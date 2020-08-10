package leetcode_inta.leetcode301_350;

import org.omg.CORBA.portable.ValueOutputStream;

/**
 * @author inta
 * @date 2020/7/26
 * @describe 给定一个整数矩阵，找出最长递增路径的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 *
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2:
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 */
public class Q329longestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        this.matrix = matrix;
        counts = new int[m][n];
        visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, m, n, Long.MIN_VALUE);
                    res = Math.max(res, counts[i][j]);
                }
            }
        }
        return res;
    }
    private int[][] counts;
    private boolean[][] visited;
    private int[][] matrix;
    private void dfs(int i, int j, int m, int n, long pre) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || matrix[i][j] <= pre) {
            return;
        }
        visited[i][j] = true;
        dfs(i + 1, j, m, n, matrix[i][j]);
        dfs(i - 1, j, m, n, matrix[i][j]);
        dfs(i, j + 1, m, n, matrix[i][j]);
        dfs(i, j - 1, m, n, matrix[i][j]);
        int up = i > 0 && matrix[i - 1][j] > matrix[i][j] ? counts[i - 1][j] : 0;
        int down = i < m - 1 && matrix[i + 1][j] > matrix[i][j] ? counts[i + 1][j] : 0;
        int left = j > 0 && matrix[i][j - 1] > matrix[i][j] ? counts[i][j - 1] : 0;
        int right = j < n - 1 && matrix[i][j + 1] > matrix[i][j] ? counts[i][j + 1] : 0;
        counts[i][j] += Math.max(up, Math.max(left, Math.max(right, down))) + 1;
    }
}
