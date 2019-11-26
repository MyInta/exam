package tencent.leetcode901_950;

/**
 * @author inta
 * @date 2019/11/26
 * @describe 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 *
 * 你可以返回满足此条件的任何数组作为答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 *
 */
public class Q905sortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) return new int[0];
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            while (right > 0 && (A[right] & 1) == 1) {
                right --;
            }
            while (left < right && (A[left] & 1) == 0) {
                left ++;
            }
            if (left >= right) {
                break;
            }
            //对遇到的两个不符合条件的数其进行交换
            int temp = A[right];
            A[right] = A[left];
            A[left] = temp;
            left ++;
            right --;
        }
        return A;
    }
}
