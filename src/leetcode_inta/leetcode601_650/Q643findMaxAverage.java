package leetcode_inta.leetcode601_650;

/**
 * @author inta
 * @date 2020/6/11
 * @describe 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例 1:
 *
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 *
 * 注意:
 *
 *     1 <= k <= n <= 30,000。
 *     所给数据范围 [-10,000，10,000]。
 *
 */
public class Q643findMaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int res = sum;
        for (int j = k; j < nums.length; j++) {
            sum -= nums[j - k] - nums[j];
            res = Math.max(res, sum);
        }
        return res * 1.0 / k;
    }
}
