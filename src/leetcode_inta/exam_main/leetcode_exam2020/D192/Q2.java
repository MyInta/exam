package leetcode_inta.exam_main.leetcode_exam2020.D192;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/6/7
 * @describe
 */
public class Q2 {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) / 2];
        int[] res = new int[k];
        int index = k - 1;
        int left = 0, right = arr.length - 1;
        while (index >= 0) {
            if (Math.abs(arr[right] - mid) >= Math.abs(arr[left] - mid)) {
                res[index] = arr[right --];
            } else {
                res[index] = arr[left ++];
            }
            index --;
        }
        return res;
    }
}
