package offer.V1_50;

import tencent.Main;

/**
 * @author inta
 * @date 2020/3/4
 * @describe 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
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
 *  LC264
 */
public class V49nthUglyNumber {
    //三指针记录2 3 5被整除的量 dp解法
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int count_2 = 0, count_3 = 0, count_5 = 0;
        for (int i = 1; i < n; i ++) {
            int min = Math.min(dp[count_2] * 2, Math.min(dp[count_3] * 3, dp[count_5] * 5));
            if (min == dp[count_2] * 2) count_2 ++;
            if (min == dp[count_3] * 3) count_3 ++;
            if (min == dp[count_5] * 5) count_5 ++;
            dp[i] = min;
        }
        return dp[n - 1];
    }
}
