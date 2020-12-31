package leetcode_inta.exam_main.leetcode_exam2020.D207;

/**
 * @author inta
 * @date 2020/9/20
 * @describe
 */
public class Q3 {

    public int maxProductPath(int[][] grid) {
        int mod = 1_000_000_007;
        int n = grid.length;
        int m = grid[0].length;
        long[][][] counts = new long[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    counts[i][j][0] = 0;
                    counts[i][j][1] = 0;
                    continue;
                }
                if (i > 0 && j > 0) {
                    long pre_i_0 = counts[i - 1][j][0];
                    long pre_i_1 = counts[i - 1][j][1];
                    long pre_j_0 = counts[i][j - 1][0];
                    long pre_j_1 = counts[i][j - 1][1];
                    long x = Math.max(pre_i_1, pre_j_1) * grid[i][j];
                    long y = Math.min(pre_i_0, pre_j_0) * grid[i][j];
                    counts[i][j][1] = Math.max(x, y);
                    counts[i][j][0] = Math.min(x, y);
                } else if (i > 0) {
                    long x = counts[i - 1][j][1] * grid[i][j];
                    long y = counts[i - 1][j][0] * grid[i][j];
                    counts[i][j][1] = Math.max(x, y);
                    counts[i][j][0] = Math.min(x, y);
                } else if (j > 0) {
                    long x = counts[i][j - 1][1] * grid[i][j];
                    long y = counts[i][j - 1][0] * grid[i][j];
                    counts[i][j][1] = Math.max(x, y);
                    counts[i][j][0] = Math.min(x, y);
                } else {
                    counts[i][j][0] = grid[i][j];
                    counts[i][j][1] = grid[i][j];
                }
            }
        }
        return counts[n - 1][m - 1][1] >= 0 ? (int) (counts[n - 1][m - 1][1] % mod) : -1;
    }
}
