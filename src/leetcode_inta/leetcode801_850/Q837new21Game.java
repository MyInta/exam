package leetcode_inta.leetcode801_850;

/**
 * @author inta
 * @date 2019/11/8
 * @describe 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 *
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。
 * 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 *
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * 示例 1：
 *
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 *
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 *
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 * 提示：
 *
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 *
 */
public class Q837new21Game {
    public double new21Game(int N, int K, int W) {
        if (K == 0) return 1.0;
        //dp[i]表示0-i的发生的概率之和
        double[] dp = new double[N + 1];
        dp[0] = 1.0;
        for (int i = 1; i <= N; i ++) {
            int min = Math.min(i, K);
            if (i <= W) {
                //P[i] = 1/W * sum[i-1]
                //sum[i] = sum[i-1] + P[i] = sum[i-1] + sum[i-1] / W     (when i <= W)
                dp[i] = dp[i - 1] + dp[min - 1]/W;
            } else if (i > W) {
                //如在求P[15]的时候，P[4]不可能取，因为W=10。P[4]+W小于15
                //P[15] = 1/W * (P[14] + P[13] + ... + P[5]) = 1/W * (sum[14] - sum[4])
                //归纳一下，对于 i > W 的情况下：
                //P[i] = 1/W * (sum[i-1] - sum[i-W-1])
                //sum[i] = sum[i-1] + P[i] = sum[i-1] + (sum[i-1] - sum[i-W-1]) / W     (when i > W)
                dp[i] = dp[i - 1] + (dp[min - 1] - dp[i - 1 - W])/W;
            }
        }
        return dp[N] - dp[K - 1];
    }
}
