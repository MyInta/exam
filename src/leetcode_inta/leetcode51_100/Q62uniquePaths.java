package leetcode_inta.leetcode51_100;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/9/27
 * @describe 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 例如，图是一个7 x 3 的网格。有多少可能的路径？
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 */
public class Q62uniquePaths {
    //使用的是记忆递归
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        return path(m, n, dp);
    }

    private int path(int m, int n, int[][] dp) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 0 || n ==0) {
            return 0;
        }
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        return dp[m][n] = path(m - 1, n, dp) + path(m, n-1, dp);
    }

    //使用动态规划
    private class Q62uniquePaths2{
        public int uniquePaths(int m, int n) {
            if (m == 0 || n == 0) {
                return 0;
            }
            int[][] dp = new int[m + 1][n + 1];
            for (int k = 1; k <= m; k++) {
                dp[k][1] = 1;
            }
            for (int k = 1; k <= n; k++) {
                dp[1][k] = 1;
            }
//            Arrays.fill(dp[1], 1);
//            dp[1][0] = 0;
            for (int i = 2; i <= m; i++) {
                for (int j = 2; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j-1];
                }
            }
            return dp[m][n];
        }
        //更优化解法
        public int uniquePaths2(int m, int n) {
            if (m == 0 || n == 0) {
                return 0;
            }
            int[] res = new int[n];
            Arrays.fill(res, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    res[j] += res[j-1];
                }
            }
            return res[n - 1];
        }
    }
}
