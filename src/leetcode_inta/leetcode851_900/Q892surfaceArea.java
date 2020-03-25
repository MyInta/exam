package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2020/3/25
 * @describe 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 *
 * 请你返回最终形体的表面积。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[2]]
 * 输出：10
 *
 * 示例 2：
 *
 * 输入：[[1,2],[3,4]]
 * 输出：34
 *
 * 示例 3：
 *
 * 输入：[[1,0],[0,2]]
 * 输出：16
 *
 * 示例 4：
 *
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 *
 * 示例 5：
 *
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 *
 *
 * 提示：
 *
 *     1 <= N <= 50
 *     0 <= grid[i][j] <= 50
 */
public class Q892surfaceArea {
    //我们用笨办法试一试，就是每个方块考虑下它上下左右前后有无，缺一就说明留一面，累加这个保留的面
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += resume(grid, i, j, n);
            }
        }
        return res;
    }
    private int resume(int[][] grid, int i, int j, int n) {
        //如果不考虑四周的话，对应的面积
        int v = grid[i][j];
        int sum = v == 0 ? 0 : 2 + v * 4;
        if (i > 0 && grid[i - 1][j] > 0) sum -= Math.min(grid[i - 1][j], v);
        if (j > 0 && grid[i][j - 1] > 0) sum -= Math.min(grid[i][j - 1], v);
        if (i < n - 1 && grid[i + 1][j] > 0) sum -= Math.min(grid[i + 1][j], v);
        if (j < n - 1 && grid[i][j + 1] > 0) sum -= Math.min(grid[i][j + 1], v);
        return sum;
    }
    //官解思路一致，不过他们是按照左上到右下的顺序，每次只减左和上的重叠，减min*2即可，而我考虑的减四周，多一倍计算量
}
