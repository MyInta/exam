package tencent.leetcode201_250;

/**
 * @author inta
 * @date 2019/12/24
 * @describe 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 */
public class Q209minSubArrayLen {
    //先简单点思路来一下,能通过，但效率很低
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i ++) {
            int count = 0;
            for (int j = i; j < nums.length; j ++) {
                count += nums[j];
                if (count >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


    //大佬提供的思路，双指针，从头开始
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int count = 0;
        while (right < nums.length) {
            count += nums[right];
            while (left <= right && count >= s) {
                //先做一下结果取最值, +1是因为取的是元素个数
                res = Math.min(res, right - left + 1);
                //然后-左边元素
                count -= nums[left];
                left ++;
            }
            //如果指针区间内累加值小于s，就将右指针右移
            right ++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

      //TODO 二分法
//    public int minSubArrayLen3(int s, int[] nums) {
//
//    }
}
