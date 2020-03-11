package leetcode_inta.leetcode51_100;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/8
 * @describe 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Q55canJump {

    public boolean canJump(int[] nums) {
        if (nums.length == 0) return true;
        dp = new boolean[nums.length];
        Arrays.fill(dp, true);
        return solution2(nums, nums.length - 1);
//        return solution(nums, nums.length - 1);
    }
    //超出时间限值，可还行，哈哈哈，说明方法是对的，只是比较费时,看看有没有冗余，解决不了只能换思路了
//    private boolean solution(int[] nums, int dest) {
//        if (dest == 0) return true;
//        for (int i = dest - 1; i >= 0; i --) {
//            int step = nums[i];
//            if (step + i >= dest) {
//                if(solution(nums, i)) return true;
//            }
//        }
//        return false;
//    }
    //然后我想到了设个boolean收集，去冗余，然后速度飞起，哈哈哈哈哈哈哈哈哈
    private boolean[] dp;
    private boolean solution2(int[] nums, int dest) {
        if (dest == 0) return true;
        for (int i = dest - 1; i >= 0; i --) {
            if (!dp[i]) continue;
            int step = nums[i];
            if (step + i >= dest) {
                if(solution2(nums, i)) return true;
                dp[i] = false;
            }
        }
        return false;
    }
}
