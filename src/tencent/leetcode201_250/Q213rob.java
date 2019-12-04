package tencent.leetcode201_250;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/12/4
 * @describe 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 */
public class Q213rob {
    //动态规划，状态转移方程 dp[i] = dp[i-1] 或者 dp[i] = dp[i-2] + nums[i]
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);
        //都有一个位置不能取，所以长度设定为len-1
        int[] dp_a = new int[len - 1];
        int[] dp_b = new int[len];
        dp_a[0] = nums[0];
        //最后一家不能取得情况下，第一家和第二家还是需要考虑是否选取哪一个的
        dp_a[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len - 1; i ++) {
            dp_a[i] = Math.max(dp_a[i - 1], dp_a[i - 2] + nums[i]);
        }
        dp_b[0] = 0;
        dp_b[1] = nums[1];
        //如果不取第二家也是有可能的，因为第一家是取不了的
        dp_b[2] = Math.max(nums[1], nums[2]);
        for (int j = 3; j < len; j ++) {
            dp_b[j] = Math.max(dp_b[j - 1], dp_b[j - 2] + nums[j]);
        }
        return Math.max(dp_a[len - 2], dp_b[len - 1]);
    }

    //看了大神的解答，思路极其简洁，使用了指针和双排列dp数组思想
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        //数组拷贝 分别为不保留首位和不保留尾部，因为两者同时存在会出现环
        return Math.max(solution(Arrays.copyOfRange(nums, 1, nums.length)),
                solution(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }
    private int solution(int[] nums) {
        //分别保存的是前一个累加值，当前最大累加值，保存当前累加值的容器
        int pre = 0,cur = 0,tmp;
        for (int num : nums) {
            //将当前累加值保存下来
            tmp = cur;
            //改变当前累加值为取（前一个累加值和目前元素之和，当前累加值）最大
            cur = Math.max(pre + num, cur);
            //将当前累加值容器内元素倒出来付给前一个累加值上
            pre = tmp;
        }
        //返回当前累加值最大情况
        return cur;
    }
}
