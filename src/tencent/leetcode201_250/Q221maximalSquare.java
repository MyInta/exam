package tencent.leetcode201_250;

/**
 * @author inta
 * @date 2019/10/15
 * @describe 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 */
public class Q221maximalSquare {
    //使用动态规划，核心在于某位置元素为1时，从左上到右下，
    // 每个元素取其左，左下，下中最小值+1设到dp中记录当前最大正方形边长
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i][j]), dp[i + 1][j]) + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }
        return res * res;
    }
}
