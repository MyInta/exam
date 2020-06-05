package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/9/25
 * @describe 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。
 *
 */
public class Q238productExceptSelf {
//    public int[] productExceptSelf(int[] nums) {
//        int[][] dp = new int[nums.length][nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            dp[i][0] = nums[i];
//            for (int j = 0; j < nums.length-i-1; j++) {
//                dp[i][j + 1] = dp[i][j]*nums[j + 1 + i];
//            }
//        }
//        int[] res = new int[nums.length];
//        //题干已经说了n>1
//        res[0] = dp[1][nums.length-2];
//        res[nums.length -1] = dp[0][nums.length - 2];
//        for (int i = 1; i < nums.length - 1; i++) {
//            res[i] = dp[0][i-1]*dp[i+1][nums.length-i-2];
//        }
//        return res;
//    }
//    public int[] productExceptSelf(int[] nums) {
//        int len = nums.length;
//        int[] inorder = new int[len];
//        int[] reverse = new int[len];
//        int[] res = new int[len];
//        inorder[0] = nums[0];
//        for (int i = 1; i < len; i++) {
//            inorder[i] = inorder[i-1]*nums[i];
//        }
//        reverse[0] = nums[len - 1];
//        for (int j = 1; j < len; j++) {
//            reverse[j] = reverse[j-1]*nums[len-1-j];
//        }
//        res[0] = reverse[len - 2];
//        res[len - 1] = inorder[len - 2];
//        for (int k = 1; k < len-1; k++) {
//            res[k] = inorder[k-1]*reverse[len - 2 - k];
//        }
//        return res;
//    }
    public int[] productExceptSelf(int[] nums) {
        //创造两个数组A、B，分别保存左边和右边的累乘，nums[i]就是A[i]*B[size - i - 1]
        int size = nums.length;
        int[] A = new int[size];
        int[] B = new int[size];
        A[0] = 1;
        B[0] = 1;
        for (int i = 1; i < size; i++) {
            A[i] = A[i - 1] * nums[i - 1];
            B[i] = B[i - 1] * nums[size - i];
        }
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = A[i] * B[size - i - 1];
        }
        return res;
    }

    //大神思路是在我上面的精简化，不用数组存储值，而是遍历两边，一遍累乘前缀，一遍累乘后缀
    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        //准备的累乘前缀值
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = k;
            k *= nums[i];
        }
        //此时相当于已经把可能的前缀累乘情况放在了结果数组中，这时候从尾部往前乘上后缀
        k = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            res[j] *= k;
            k *= nums[j];
        }
        return res;
    }
}
