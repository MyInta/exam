package tencent.exam_main.leetcodeD162;

/**
 * @author inta
 * @date 2019/11/10
 * @describe
 */
public class Q5257 {
    private boolean surround = true;
    public int closedIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j <grid[0].length; j ++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    if (surround) {
                        res ++;
                    } else {
                        surround = true;
                    }
                }
            }
        }
        return res;
    }
    private void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            surround = false;
            return;
        }
        if (visited[i][j] || grid[i][j] == 1) {
            return;
        }
        visited[i][j] = true;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i, j + 1);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j - 1);
    }
}
