package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2020/1/4
 * @describe 给定一个包含了一些 0 和 1的非空二维数组 grid ,
 * 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 */
public class Q695maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        if (grid == null || grid.length == 0) return res;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j, n, m);
                    res = Math.max(res, count);
                    count = 0;
                }
            }
        }
        return res;
    }
    private int count;
    private void dfs(int[][] grid, boolean[][] visited, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || visited[i][j] || grid[i][j] == 0) return;
        visited[i][j] = true;
        count ++;
        dfs(grid, visited, i + 1, j, n, m);
        dfs(grid, visited, i - 1, j, n, m);
        dfs(grid, visited, i, j + 1, n, m);
        dfs(grid, visited, i, j - 1, n, m);
    }
}
