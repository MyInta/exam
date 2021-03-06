package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2020/1/20
 * @describe 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 * 注意：数组长度不会超过10000。
 * 提示：
 * 0 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class Q674findLengthOfLCIS {
    public int findLengthOfLCIS(int[] nums) {
        int res = 0;
        long little = Long.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            // 如果递增就添加数值，否则重置
            if (num > little) {
                count ++;
            } else {
                res = Math.max(res, count);
                count = 1;
            }
            little = num;
        }
        return Math.max(res, count);
    }

    // 这不就是双指针吗？
    public int findLengthOfLCIS2(int[] nums) {
        int left = 0;
        int right = 1;
        int max = 0;
        while (left < nums.length) {
            while (right < nums.length && nums[right] > nums[right - 1]) {
                right++;
            }
            max = Math.max(max, right - left);
            left = right;
            right++;
        }
        return max;
    }
}
