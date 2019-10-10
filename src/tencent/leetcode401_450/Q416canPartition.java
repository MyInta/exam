package tencent.leetcode401_450;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/10
 * @describe 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 */
public class Q416canPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果是奇数
        if ((sum & 1) == 1) return false;
        int target = sum >> 1;
        boolean[][] dp = new boolean[nums.length][target + 1];
        //第一行，nums索引为0,i从零开始，是因为nums为正整数，不存在0
        for (int i = 1; i < target + 1; i ++) {
            if (nums[0] == i) {
                //找到数组中值和目标相等的，即可以实现抓取完毕，设为true
                dp[0][i] = true;
            }
        }
        for (int i = 1; i < nums.length; i ++) {
            for (int j = 0; j < target + 1; j ++) {
                //默认下，若不删除元素，能否取完的状态和之前是一致的
                dp[i][j] = dp[i - 1][j];
                //如果考虑要删除元素
                if (j >= nums[i]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
