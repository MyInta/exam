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

    //整理思路：最后一次取，必定是在[K,K+W)范围内，胜率在于[K,K+w]中N处于哪里，胜率为获得分数N以下的概率和
    public double new21Game2(int N, int K, int W) {
        //由思路，我们先把两种极端给了(题意说K <= N);if (N < K) return 0;不存在
        if (N > K + W) return 1;
        double[] dp = new double[K + W]; //取不到K+W
        double sum = 0;
        for (int i = K; i < K + W; i++) {
            //先确定[K,K+W)范围内胜率，小于等于N必定1，否则0
            dp[i] = i <= N ? 1 : 0;
            //统计这一次抽取获胜的总胜率和
            sum += dp[i];
        }
        for (int j = K - 1; j >= 0; j--) {
            //然后W长度窗口左移一格，新的一格获胜的概率是终点胜率基础上，一次抽取1/W的概率累乘，即sum * 1/W
            dp[j] = sum / W;
            //此时更新W窗口内胜率和，变为减去最右端胜率，加上新一格胜率之和
            sum = sum - dp[j + W] + dp[j];
        }
        //综上，理解计算胜率的方式，就是维持一个W长度窗口，统计窗口内胜率和SUM，
        // 然后移动窗口时修改窗口内胜率和，新格子胜率是窗口内胜率基础上抽取一次的结果
        return dp[0];
    }

    //又看到一个大神的思路，从头开始计算胜率
    public double new21Game3(int N, int K, int W) {
        if (K == 0) return 1;
        double[] dp = new double[N + 1];
        dp[0] = 1;
        //累加W窗口内胜率和
        double sum = 1;
        double ans = 0;
        for (int i = 1; i <= N; i++) {
            //同上一方法思路，窗口内胜率基础上一次抽取1/W
            dp[i] = sum / W;
            //如果没有达到K值限定，那么就是可以不断添加卡牌，也就是把各种分值dp[i]累加起来即可
            if (i < K) sum += dp[i];
            //超过W窗口，抽取一次时不一定能涵盖，所以维持W窗口胜率时，需要把最左边取不到的情况去掉
            if (i >= W) sum -= dp[i - W];
        }
        //此时sum是窗口长度W内的胜率和，dp[i]代表分值i取到时候的概率
        for (int j = K; j <= N; j++) {
            //那么获得题意得胜率，也就是取得[K,N]区间分值得概率和
            ans += dp[j];
        }
        return ans;
    }
}
