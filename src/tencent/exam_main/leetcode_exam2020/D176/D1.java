package tencent.exam_main.leetcode_exam2020.D176;

/**
 * @author inta
 * @date 2020/2/16
 * @describe
 */
public class D1 {
    public int countNegatives(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (grid[i][j] < 0) res ++;
            }
        }
        return res;
    }
}
