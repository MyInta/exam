package leetcode_inta.leetcode301_350;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/7/24
 * @describe 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class Q322CoinChange {
    //使用dp的思想  通俗点来理解就是1金额我们要达到，用硬币最少数量是dp[i]，
    // n金额要达到，那肯定是min(dp[n - 硬币i面额] + 1,dp[n - 硬币j面额] + 1, ...)
    public int coinChange(int[] coins, int amount) {
        int len = amount + 1;
        //存储amount+1长度的数组，dp[i]表示总计i需要最少多少枚硬币
        int[] dp = new int[len];
        //默认下每个位置都是最大数量len个，因为硬币至少面额1，amount下数量撑死amount个
        Arrays.fill(dp, len);
        //amount0的时候，我们啥也不取就可以做到，需要硬币数量为0
        dp[0] = 0;
        //遍历所有金额
        for (int i = 1; i <= amount; i ++) {
            //然后对应每个金额下，我们循环考虑所有的硬币
            for (int j = 0; j < coins.length; j ++) {
                //当该硬币面值小于我们的总计金额时，可以考虑要不要他
                if (coins[j] <= i) {
                    //不要就是原样，要就是消减总额后，数量+1，取两者中数量最少就可以做到的
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == len ? -1 : dp[amount];
    }


    //dfs + 剪枝
    private int res = Integer.MAX_VALUE;
    public int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        if (coins.length < 1) return -1;
        dfs(coins, amount, coins.length - 1, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    private void dfs(int[] coins, int target, int index, int count) {
        //索引越界，或者统计的可能的数量比前面记录的结果数量要长也直接返回
        if (index < 0 || count + target / coins[index] >= res) return;
        if ((target % coins[index]) == 0) {
            res = Math.min(res, count + (target / coins[index]));
            return ;
        }
        //index位置的元素选取0-最大数量个(优先取最大个数)，接着递归
        for (int i = target / coins[index]; i >= 0; i --) {
            dfs(coins, target - i * coins[index], index - 1, count + i);
        }
    }



}
