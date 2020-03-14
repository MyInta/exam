package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/14
 * @describe 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class Q152maxProduct {
    //暴力算法
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int res = nums[0];
        for (int i = 1; i <= len; i ++) {
            for (int j = 0; j <= len - i; j ++) {
                int temp = 1;
                for (int k = 0; k < i; k ++) {
                    temp *= nums[j + k];
                }
                res = Math.max(res, temp);
            }
        }
        return res;
    }
    //优化暴力算法,结果说内存LE,然后将二维缩成一维的，总算是过了，但是，效率与内存均为糟糕
    public int maxProduct2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int res = nums[0];
        //代表从数组哪个索引开始的
        int[] dp = new int[len];
        for (int i = 1; i <= len; i ++) {
            for (int j = 0; j <= len - i; j ++) {
                int temp;
                if (dp[j] != 0) {
                    temp = dp[j];
                } else {
                    temp = 1;
                }
                temp *= nums[j + i - 1];
                dp[j] = temp;
                res = Math.max(res, temp);
            }
        }
        return res;
    }
    //看了大佬之后的思维
    public int maxProduct3(int[] nums) {
        if (nums.length == 0) return 0;
        int res = Integer.MIN_VALUE;
        int curMin = 1;
        int curMax = 1;
        for (int i = 0; i < nums.length; i ++) {
            //当遍历到的数为负时候，当前最大与最小值互换
            if (nums[i] < 0) {
                int temp = curMin;
                curMin = curMax;
                curMax = temp;
            }
            curMin = Math.min(curMin * nums[i], nums[i]);
            curMax = Math.max(curMax * nums[i], nums[i]);

            res = Math.max(res, curMax);
        }
        return res;
    }
}
