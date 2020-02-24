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
}
