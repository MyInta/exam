package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/9/4
 * @describe 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class Q53MaxSubArray {
    //动态规划
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1;i < nums.length; i++) {
            if (dp[i - 1] >= 0){
                dp[i] = dp[i - 1] +nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i: nums) {
            if (sum > 0) {
                sum += i;
            } else {
                sum = i;
            }
            res = Math.max(res, sum);
        }
        return res;
    }


}
