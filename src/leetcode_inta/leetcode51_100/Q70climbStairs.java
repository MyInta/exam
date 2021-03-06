package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/9/27
 * @describe 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class Q70climbStairs {
    //记忆化递归
    private int count = 0;
    private int[] dp;
    public int climbStairs(int n) {
        if (n <= 2) {
            return n == 1 ? 1 : 2;
        }
        dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        return solution(n);
    }
    private int solution(int n) {
        if (dp[n-1] != 0) {
            return dp[n - 1];
        }
        return dp[n -1] =  solution(n-1) + solution(n-2);
    }

    //使用动态规划（感觉是上面记忆递归的推进版本）
    private class Q70climbStairs2{
        public int climbStairs(int n) {
            if (n <= 0) return 1;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

    //一年后再做，这也太基础了
    public int climbStairs2(int n) {
        int[] dp = new int[n];
        if (n == 1) return 1;
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
