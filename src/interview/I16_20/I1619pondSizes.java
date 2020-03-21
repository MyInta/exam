package interview.I16_20;

import java.util.*;

/**
 * @author inta
 * @date 2020/3/21
 * @describe 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
 * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
 * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 *
 * 提示：
 *
 *     0 < len(land) <= 1000
 *     0 < len(land[i]) <= 1000
 */
public class I1619pondSizes {

    public int[] pondSizes(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        visited = new boolean[n][m];
        grids = land;
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && land[i][j] == 0) {
                    count = 0;
                    dfs(i, j, n, m);
                    lists.add(count);
                }
            }
        }
        int[] res = new int[lists.size()];
        int index = 0;
        for (int i : lists) {
            res[index ++] = i;
        }
        Arrays.sort(res);
        return res;
    }
    private int count;
    private boolean[][] visited;
    private int[][] grids;
    private void dfs(int i, int j, int n, int m) {
        if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || grids[i][j] != 0) return;
        visited[i][j] = true;
        count ++;
        dfs(i + 1, j, n, m);
        dfs(i - 1, j, n, m);
        dfs(i + 1, j + 1, n, m);
        dfs(i + 1, j - 1, n, m);
        dfs(i - 1, j + 1, n, m);
        dfs(i - 1, j - 1, n, m);
        dfs(i, j - 1, n, m);
        dfs(i, j + 1, n, m);
    }
}
