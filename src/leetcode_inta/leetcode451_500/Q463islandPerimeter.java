package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2020/1/7
 * @describe 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 *
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，
 * 但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *  
 *
 * 示例 :
 *
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * 输出: 16
 *
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 *
 */
public class Q463islandPerimeter {
    //dfs 两种方向考虑，添加的话，考虑周边每存在一个岛屿周长-1，考虑减少的话，所有格子*4，每遇到空格-2
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        //根据题意，长度和宽度是一致的
        if (grid == null || grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                //如果是岛屿，统计周长,周围考虑原先左边和上边，每存在一个岛屿，周长-2
                if (grid[i][j] == 1) {
                    sum += 4;
                    if (i > 0 && grid[i - 1][j] == 1) sum -= 2;
                    if (j > 0 && grid[i][j - 1] == 1) sum -= 2;
                }
            }
        }
        return sum;
    }
}
