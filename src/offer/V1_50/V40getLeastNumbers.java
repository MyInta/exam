package offer.V1_50;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/2/22
 * @describe 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 *
 */
public class V40getLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        //使用优先队列直接能求得,默认最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a : arr) {
            pq.add(a);
        }
        int[] res = new int[k];
        while (k > 0) {
            res[-- k] = pq.poll();
        }
        return res;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i ++) {
            res[i] = arr[i];
        }
        return res;
    }


    //快排思想
    public int[] getLeastNumbers3(int[] arr, int k) {
        if (k == 0) return new int[0];
        target = k - 1;
        return quickSort(arr, 0, arr.length - 1);
    }
    private int target;
    private int[] quickSort(int[] arr, int left, int right) {
        //一般是取随机值
        int index = partion(arr, left, right);
        if (index == target) {
            int[] res = new int[target + 1];
            for (int i = 0; i <= target; i++) {
                res[i] = arr[i];
            }
            return res;
        } else if (index < target) {
            return quickSort(arr, index + 1, right);
        } else {
            return quickSort(arr, left, index - 1);
        }
    }

    private int partion(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left, j = right + 1;
        while (true) {
            while (i < right && arr[++ i] < pivot) {}
            while (left < j && arr[-- j] > pivot) {}
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, left, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
