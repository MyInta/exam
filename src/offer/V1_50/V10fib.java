package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/18
 * @describe 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * 0 <= n <= 100
 */
public class V10fib {
    private int mol = 1_000_000_007;
    public int fib(int n) {
        if (n < 2) return n == 0 ? 0 : 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i ++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mol;
        }
        return dp[n];
    }

    //改造为非数组模式
    public int fib2(int n) {
        if (n < 2) return n == 0 ? 0 : 1;
        int first = 0;
        int sec = 1;
        for (int i = 2; i <= n; i ++) {
            int temp = (first + sec) % mol;
            first = sec;
            sec = temp;
        }
        return sec;
    }
}
