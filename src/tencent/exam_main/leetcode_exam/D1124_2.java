package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/11/24
 * @describe
 */
public class D1124_2 {
//    private int res = 0;
//    private int count = 0;
//    public int countServers(int[][] grid) {
//        if (grid == null || grid.length == 0) return 0;
//        int n = grid.length;
//        int m = grid[0].length;
//        boolean[][] visited = new boolean[n][m];
//        for (int i = 0; i < n; i ++) {
//            for (int j = 0; j < m; j ++) {
//                if (!visited[i][j] && grid[i][j] == 1) {
//                    dfs(grid, i, j, n, m, visited);
//                    if (count >= 2) {
//                        res += count;
//                    }
//                    count = 0;
//                }
//            }
//        }
//        return res;
//    }
//    private void dfs(int[][] grid, int i, int j, int n, int m, boolean[][] visited) {
//        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || grid[i][j] == 0) {
//            return;
//        }
//        visited[i][j] = true;
//        count ++;
//        dfs(grid, i + 1, j, n, m, visited);
//        dfs(grid, i - 1, j, n, m, visited);
//        dfs(grid, i, j + 1, n, m, visited);
//        dfs(grid, i, j - 1, n, m, visited);
//    }

    public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int[] count_m = new int[m];
        int[] count_n = new int[n];
        int dou = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (grid[i][j] == 1) {
                    count_n[i] ++;
                    count_m[j] ++;
                }
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (grid[i][j] == 1 && count_m[j] >= 2 && count_n[i] >= 2) {
                    dou ++;
                }
            }
        }
        int res = 0;
        for (int cn : count_n) {
            if (cn >= 2) res += cn;
        }
        for (int cm : count_m) {
            if (cm >= 2) res += cm;
        }
        return res - dou;
    }
}
