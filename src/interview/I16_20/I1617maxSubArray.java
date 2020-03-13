package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/13
 * @describe 给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 *
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class I1617maxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int right = 0, res = nums[0], count = 0;
        while (right < nums.length) {
            count += nums[right];
            res = Math.max(res, count);
            if (count < 0) count = 0;
            right ++;
        }
        return res;
    }
}
