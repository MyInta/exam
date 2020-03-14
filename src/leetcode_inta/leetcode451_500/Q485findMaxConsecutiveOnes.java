package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/12/16
 * @describe 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 *
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 */
public class Q485findMaxConsecutiveOnes {
    //暴力
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int left = -1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) {
                left = i;
            } else {
                while (i < nums.length && nums[i] == 1) {
                    i ++;
                }
                res = Math.max(res, i - left - 1);
                //更新左边界
                left = i;
            }
        }
        return res;
    }
}
