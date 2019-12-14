package tencent.leetcode901_950;

/**
 * @author inta
 * @date 2019/12/14
 * @describe 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 *
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 */
public class Q922sortArrayByParityII {
    //双指针应该能快速做掉
    public int[] sortArrayByParityII(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < A.length) {
            if (A[left] % 2 == 1) {
                while (right > 0 && A[right] % 2 == 1) {
                    right -= 2;
                }
                //题意得数量上left与right相异得情况是一致得，不用担心越界 直接swap
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
            left += 2;
        }
        return A;
    }
}
