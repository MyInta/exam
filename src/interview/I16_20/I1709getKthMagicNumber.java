package interview.I16_20;

/**
 * @author inta
 * @date 2020/4/22
 * @describe 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
 * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。
 * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 *
 * 输入: k = 5
 *
 * 输出: 9
 *
 */
public class I1709getKthMagicNumber {
    //看了网友思路才有，类似的题目哪里遇到过给忘了
    public int getKthMagicNumber(int k) {
        //不存在
        if (k <= 0) return -1;
        int[] res = new int[k];
        res[0] = 1;
        int count_3 = 0, count_5 = 0, count_7 = 0;
        //res[0]已被内定，所以从1开始
        for (int i = 1; i < k; i++) {
            //从3、5、7中找乘积最小的那个，避免一直同乘某个数，按顺序3、5、7来，使用了count系列
            int value = Math.min(Math.min(res[count_3] * 3, res[count_5] * 5), res[count_7] * 7);
            if (value % 3 == 0) {
                count_3 ++;
            }
            if (value % 5 == 0) {
                count_5 ++;
            }
            if (value % 7 == 0) {
                count_7 ++;
            }
            res[i] = value;
        }
        return res[k - 1];
    }

    ///化身dp
    public int getKthMagicNumber2(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int count_3 = 0, count_5 = 0, count_7 = 0;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(Math.min(dp[count_3] * 3, dp[count_5] * 5), dp[count_7] * 7);
            if (dp[i] == dp[count_3] * 3) count_3 ++;
            if (dp[i] == dp[count_5] * 5) count_5 ++;
            if (dp[i] == dp[count_7] * 7) count_7 ++;
        }
        return dp[k - 1];
    }
}
