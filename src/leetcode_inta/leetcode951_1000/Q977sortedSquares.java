package leetcode_inta.leetcode951_1000;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/12/17
 * @describe 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 */
public class Q977sortedSquares {
    //暴力
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i ++) {
            A[i] = A[i] * A[i];
        }
        Arrays.sort(A);
        return A;
    }
    //双指针
    public int[] sortedSquares2(int[] A) {
        int len = A.length;
        int left = 0, right = len - 1;
        int[] res = new int[len];
        int n = 1;
        while (left <= right) {
            if (Math.abs(A[left]) >= Math.abs(A[right])) {
                res[len - n] = A[left] * A[left];
                left ++;
                n ++;
            } else {
                res[len - n] = A[right] * A[right];
                right --;
                n ++;
            }
        }
        return res;
    }
}
