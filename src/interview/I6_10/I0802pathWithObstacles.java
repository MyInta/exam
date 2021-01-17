package interview.I6_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/20
 * @describe 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，
 * 但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 *
 * 说明：r 和 c 的值均不超过 100。
 */
public class I0802pathWithObstacles {

    private int[][] grids;
    private int m;
    private int n;

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        this.n = obstacleGrid.length;
        this.m = obstacleGrid[0].length;
        this.grids = obstacleGrid;
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, 0, new boolean[n][m], ans);
        return ans;
    }

    private boolean dfs(int row, int col, boolean[][] visited, List<List<Integer>> paths) {
        if (row >= n || col >= m || visited[row][col] || grids[row][col] == 1) return false;
        paths.add(Arrays.asList(row, col));
        if (row == n - 1 && col == m - 1) return true;
        visited[row][col] = true;
        if (dfs(row + 1, col, visited, paths) || dfs(row, col + 1, visited, paths)) return true;
        paths.remove(paths.size() - 1);
        return false;
    }
}
