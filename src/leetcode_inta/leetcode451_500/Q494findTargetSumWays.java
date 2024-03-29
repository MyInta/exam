package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/10/15
 * @describe 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例 1:
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * 注意:
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。
 */
public class Q494findTargetSumWays {
    // 使用dfs深度遍历
    public int findTargetSumWays(int[] nums, int S) {
        return solution(nums, 0, S);
    }
    private int solution(int[] nums, int start, int target) {
        if (start == nums.length) {
            // 如果减到0，说明可行，否则就弃之
            return target == 0 ? 1 : 0;
        }
        // 左移一种选择，一种不选择，然后深度遍历到一圈都考虑过后，判断是否为所要值
        return solution(nums, start + 1, target + nums[start])
                + solution(nums, start + 1, target - nums[start]);
    }

    // 反过来想，累加和，找差值一半的目标
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        int newTarget = (sum + target) / 2;
        // 此时就是找和为newTarget的元素组合
        int[] dp = new int[newTarget + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = newTarget; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[newTarget];
    }
}
