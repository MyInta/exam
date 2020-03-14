package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2020/2/1
 * @describe 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 *
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 */
public class Q188maxProfit {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        //不存在交易，自然为0
        if (k < 1) return 0;
        //如果k大于等于prices的一半，说明相当于可以无限多次交易，即Q122的解
        if (k >= len / 2) {
            int res = 0;
            for (int i = 0; i < len - 1; i ++) {
                if (prices[i + 1] > prices[i]) res += prices[i + 1] - prices[i];
            }
            return res;
        }
        //否则的话就是存在[1,prices.length/2)次内的交易
        //dp[i][k][0]代表i位置 k次操作下 没有持股状态获得利润； dp[i][k][1]表示持有股状态时候的利润
        int[][][] dp = new int[len][k + 1][2];
        //初始化
        for (int i = 1; i <= k; i ++) {
            //一开始未持有0
            dp[0][i][0] = 0;
            //一开始持有股票利润为-该股票价值
            dp[0][i][1] = -prices[0];
        }
        //动规
        for (int i = 1; i < len; i ++) {
            //倒着来，从末尾往前
            for (int j = k; j >= 1; j --) {
                //此时未持有股票状态最优为之前未持有最优和之前持有但卖掉了获得利润总和之间最大值
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                //考虑啥也不干和当前买入股票的情况,并且如果有新的买入，说明操作次数较上次肯定是增加1个，故上次操作次数为j-1
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        //再怎么说，也不会是持有股票状态，赔本买卖利润肯定会减少
        return dp[len - 1][k][0];
    }


    //能否化为1维元素？
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        //不存在交易，自然为0
        if (k < 1) return 0;
        //如果k大于等于prices的一半，说明相当于可以无限多次交易，即Q122的解
        if (k >= len / 2) {
            int res = 0;
            for (int i = 0; i < len - 1; i ++) {
                if (prices[i + 1] > prices[i]) res += prices[i + 1] - prices[i];
            }
            return res;
        }
        int[] dp0 = new int[k + 1];
        int[] dp1 = new int[k + 1];
        //初始化
        for (int i = 1; i <= k; i ++) {
            dp0[i] = 0;
            dp1[i] = -prices[0];
        }
        for (int price : prices) {
            for (int i = k; i > 1; i --) {
                dp0[i] = Math.max(dp0[i], dp1[i] + price);
                //只有在新一轮买入操作上，才考虑次数k的变化
                dp1[i] = Math.max(dp1[i], dp0[i - 1] - price);
            }
            dp0[1] = Math.max(dp0[1], dp1[1] + price);
            dp1[1] = Math.max(dp1[1], -price);
        }
        return dp0[k];
    }
}
