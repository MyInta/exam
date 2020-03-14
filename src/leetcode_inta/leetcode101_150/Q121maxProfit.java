package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/9/26
 * @describe 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class Q121maxProfit {

    //动态规划解法一，效率极低
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    dp[j] = Math.max(dp[j], prices[j] - prices[i]);
                }
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }
        return dp[prices.length - 1];
    }
    //暴力破解，效率也极低
    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    res = Math.max(res, prices[j] - prices[i]);
                }
            }
        }
        return res;
    }
    //取巧，递进找谷底与谷峰之间差值(并非绝对意义上的谷底与谷峰)
    public int maxProfit3(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > res) {
                res = prices[i] - minPrice;
            }
        }
        return res;
    }
}
