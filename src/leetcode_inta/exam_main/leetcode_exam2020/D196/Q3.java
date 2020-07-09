package leetcode_inta.exam_main.leetcode_exam2020.D196;

/**
 * @author inta
 * @date 2020/7/5
 * @describe
 */
public class Q3 {
    //暴力求解下
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        findZero = new boolean[n][m];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                res += findCount(mat, i, j, n, m);
            }
        }
        return res;
    }
    private boolean[][] findZero;
    private int findCount(int[][] mat, int x, int y, int n, int m) {
        int res = 0;
        for (int i = 0; i <= n - x; i++) {
            for (int j = 0; j <= m - y; j++) {
                if (findZero[i][j]) continue;
                boolean mark = true;
                for (int k = i; k < i+ x; k++) {
                    boolean find = true;
                    int p = j;
                    for (; p < j + y; p++) {
                        if (mat[k][p] == 0) {
                            find = false;
                            findZero[k][p] = true;
                            break;
                        }
                    }
                    if (!find) {
                        mark = false;
                        findZero[k][p] = true;
                        break;
                    }
                }
                if (mark) {
                    res ++;
                }

            }
        }
        return res;
    }
}
