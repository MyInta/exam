package interview.I16_20;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/3/14
 * @describe 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 *
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 */
public class I1714smallestK {
    public int[] smallestK(int[] arr, int k) {
        //暴力优先队列
        PriorityQueue<Integer> p = new PriorityQueue<>((a, b) -> b - a);
        //题意说了k符合长度要求
        for (int i = 0; i < k; i ++) {
            p.add(arr[i]);
        }
        for (int j = k; j < arr.length; j ++) {
            if (arr[j] < p.peek()) {
                p.poll();
                p.add(arr[j]);
            }
        }
        int[] res = new int[k];
        int index = 0;
        while (!p.isEmpty()) {
            res[index ++] = p.poll();
        }
        return res;

        //效率不高，但是是一个维护K尺寸的优先队列
//        if (k == 0) return new int[0];
//        PriorityQueue<Integer> p = new PriorityQueue<>((a, b) -> b - a);
//        //题意说了k符合长度要求
//        for (int i = 0; i < k; i ++) {
//            p.add(arr[i]);
//        }
//        for (int j = k; j < arr.length; j ++) {
//            if (arr[j] < p.peek()) {
//                p.poll();
//                p.add(arr[j]);
//            }
//        }
//        int[] res = new int[k];
//        int index = 0;
//        while (!p.isEmpty()) {
//            res[index ++] = p.poll();
//        }
//        return res;
    }

    public int[] smallestK2(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }
}
