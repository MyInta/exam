package leetcode_inta.leetcode1301_1350;

import javafx.concurrent.Worker;

/**
 * @author inta
 * @date 2020/11/3
 * @describe 给你一个 m * n 的矩阵 mat 和一个整数 K ，
 * 请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 *
 *     i - K <= r <= i + K, j - K <= c <= j + K
 *     (r, c) 在矩阵内。
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * 输出：[[12,21,16],[27,45,33],[24,39,28]]
 *
 * 示例 2：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * 输出：[[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 *
 * 提示：
 *
 *     m == mat.length
 *     n == mat[i].length
 *     1 <= m, n, K <= 100
 *     1 <= mat[i][j] <= 100
 *
 */
public class Q1314matrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] answer = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = solution(mat, i, j, K);
            }
        }
        return answer;
    }
    private int solution(int[][] mat, int row, int col, int K) {
        int res = 0;
        for (int i = row - K; i <= row + K && i < mat.length; i++) {
            if (i < 0) continue;
            for (int j = col - K; j <= col + K && j < mat[0].length; j++) {
                if (j < 0) continue;
                res += mat[i][j];
            }
        }
        return res;
    }


    //看了大佬的思路，求一个前缀和，避免重复的累加运算
    public int[][] matrixBlockSum2(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] answer = new int[m][n];
        //为了方便运算，前缀和的矩阵我们范围放大一层
        pre = new int[m + 1][n + 1];
        for (int i = 0 ;i < m; i++) {
            for (int j = 0; j < n; j++) {
                pre[i + 1][j + 1] = pre[i][j + 1] + pre[i + 1][j] - pre[i][j] + mat[i][j];
            }
        }
        for (int i = 0 ;i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row1 = Math.max(0, i - K);
                int col1 = Math.max(0, j - K);
                int row2 = Math.min(m - 1, i + K);
                int col2 = Math.min(n - 1, j + K);
                answer[i][j] = getPre(row1, col1, row2, col2);
            }
        }
        return answer;
    }
    private int[][] pre;
    //获取前缀和
    private int getPre(int row1, int col1, int row2, int col2) {
        return pre[row2 + 1][col2 + 1] - pre[row1][col2 + 1] - pre[row2 + 1][col1] + pre[row1][col1];
    }
}
