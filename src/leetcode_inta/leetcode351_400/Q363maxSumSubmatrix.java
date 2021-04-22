package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2021/4/22
 * @describe 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * 示例 1：
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 */
public class Q363maxSumSubmatrix {
    // 统计矩形区域，重新计算耗时，选用动规保存记录
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][][][] dp = new int[m][n][m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[0][0][i][j] = matrix[i - 1][j - 1] + dp[0][0][i - 1][j] + dp[0][0][i][j - 1] - dp[0][0][i - 1][j - 1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int row = i + 1; row <= m; row++) {
                    for (int col = j + 1; col <= n; col++) {
                        if (i == row - 1 && j == col - 1) {
                            dp[i][j][row][col] = matrix[i][j];
                            if (dp[i][j][row][col] == k) {
                                return k;
                            } else if (dp[i][j][row][col] < k) {
                                res = Math.max(res, dp[i][j][row][col]);
                            }
                            continue;
                        }
//                        System.out.println(i + "-" + j + "-" + row + "-" + col);
                        int left = j - 1 >= 0 ? dp[0][0][row][j - 1] : 0;
                        int up = i - 1 >= 0 ? dp[0][0][i - 1][col] : 0;
                        int pre = i - 1 >= 0 && j - 1 >= 0 ? dp[0][0][i - 1][j - 1] : 0;
                        dp[i][j][row][col] = dp[0][0][row][col] - left - up + pre;
                        if (dp[i][j][row][col] == k) {
                            return k;
                        } else if (dp[i][j][row][col] < k) {
                            res = Math.max(res, dp[i][j][row][col]);
                        }
                    }
                }
            }
        }
        return res;
    }
}
