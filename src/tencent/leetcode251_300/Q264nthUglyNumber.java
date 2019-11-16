package tencent.leetcode251_300;

import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/11/16
 * @describe 编写一个程序，找出第 n 个丑数。
 *
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 *
 * 1 是丑数。
 * n 不超过1690。
 *
 */
public class Q264nthUglyNumber {
    //核心：丑数可以被质子整除，用三个指针记录被整除的量
    public int nthUglyNumber(int n) {
        int i_2 = 0, i_3 = 0, i_5 = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i ++) {
            int min = Math.min(dp[i_2] * 2, Math.min(dp[i_3] * 3, dp[i_5] * 5));
            if (min == dp[i_2] * 2) i_2 ++;
            if (min == dp[i_3] * 3) i_3 ++;
            if (min == dp[i_5] * 5) i_5 ++;
            dp[i] = min;
        }
        return dp[n - 1];
    }


}
