package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/11/26
 * @describe 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 */
public class Q45jump {
    //暴力解法费时
    private int res = Integer.MAX_VALUE;
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        dfs(nums, 0, nums.length - 1, 0);
        return res;
    }
    private void dfs(int[] nums, int start, int end, int step) {
        if (start >= end) {
            res = Math.min(res, step);
            return;
        }
        int num = nums[start];
        if (start + num >= end) {
            res = Math.min(res, step + 1);
            return;
        }
        //找跳跃区间内的最大值
        int max_num = Integer.MIN_VALUE;
        int max_index = start + 1;
        for (int i = 1; i <= num; i ++) {
            if (nums[start + i] + i + start > max_num) {
                //更新最大值
                max_num = nums[start + i] + i + start;
                max_index = i + start;
            }
        }
        dfs(nums, max_index, end, step + 1);
    }

}
