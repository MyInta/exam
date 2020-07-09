package leetcode_inta.exam_main.leetcode_exam2020.D196;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/7/5
 * @describe
 */
public class Q1 {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int dif = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != dif) return false;
        }
        return true;
    }

}
