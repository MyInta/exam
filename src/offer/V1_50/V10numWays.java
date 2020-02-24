package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/23
 * @describe 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 提示：
 *
 * 0 <= n <= 100
 *  同LC509
 */
public class V10numWays {
    //dp做
    public int numWays(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i ++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
        }
        return dp[n - 1];
    }

    //网友简洁版
    public int numWays2(int n) {
        if (n < 2) return 1;
        int a = 1, b = 1, sum = 0;
        for (int i = 1; i < n; i ++) {
            sum = (a + b) % 1_000_000_007;
            a = b;
            b = sum;
        }
        return sum;
    }
}
