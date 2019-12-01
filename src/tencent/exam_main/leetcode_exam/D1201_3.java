package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/1
 * @describe
 */
public class D1201_3 {
    public int countSquares(int[][] matrix) {
        int res = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int min = Math.min(n, m);
        for (int k = 1; k <= min; k ++) {
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < m; j ++) {
                    if (matrix[i][j] == 1 && isV(matrix, i, j, n - 1, m - 1, k)) {
                        res ++;
                    }
                }
            }
        }
        return res;
    }
    private boolean isV(int[][] matrix, int start, int sj, int se1, int se2, int k) {
        if (start + k - 1 > se1 || sj + k - 1 > se2) {
            return false;
        }
        for (int i = start; i < start + k; i ++) {
            for (int j = sj; j < sj + k; j ++) {
                if (matrix[i][j] == 0) return false;
            }
        }
        return true;
    }
}
