package interview.I6_10;

/**
 * @author inta
 * @date 2020/4/23
 * @describe 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，
 * 编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 *
 * 示例2:
 *
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 *
 * 说明：
 *
 * 注意:
 *
 * 你可以假设：
 *
 *     0 <= n (总金额) <= 1000000
 *
 */
public class I0811waysToChange {
    //知道是动规，但是好久没做就缺少模板思路
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        final int MOD = 1_000_000_007;
        int[] coins = {1, 5, 10, 25};
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]]) % MOD;
            }
        }
        return dp[n];
    }
}
