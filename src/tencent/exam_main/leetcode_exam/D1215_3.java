package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/15
 * @describe
 */
public class D1215_3 {
    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        int min = Math.min(n, m);
        int result = 0;
        int v = 0;
        for (int k = 1; k <= min; k ++) {
            boolean flag = false;
            for (int i = 0; i < n - k + 1; i ++) {
                for (int j = 0; j < m - k + 1; j ++) {
                    int sum = sum(mat, i, j, i + k - 1, j + k - 1);
                    if (sum <= threshold) {
                        flag = true;
                        result = k;
                        break;
                    }
                }
                if (flag) break;
                if (i == n - k) return result;
            }
        }
        return result;
    }
    private int sum(int[][] mat, int start_x, int start_y, int end_x, int end_y) {
        int res = 0;
        for (int i = start_x; i <= end_x; i ++) {
            for (int j = start_y; j <= end_y; j ++) {
                res += mat[i][j];
            }
        }
        return res;
    }
}
