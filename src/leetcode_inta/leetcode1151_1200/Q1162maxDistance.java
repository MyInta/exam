package leetcode_inta.leetcode1151_1200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/3/29
 * @describe 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
 * 其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
 *
 * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 * 如果我们的地图上只有陆地或者海洋，请返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
 *
 * 示例 2：
 *
 * 输入：[[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 *
 *
 *
 * 提示：
 *
 *     1 <= grid.length == grid[0].length <= 100
 *     grid[i][j] 不是 0 就是 1
 */
public class Q1162maxDistance {
    //只看一个思路，按照现有的陆地进行填海造陆，直到不能再扩散
    public int maxDistance(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) queue.add(new int[]{i, j});
            }
        }
        //此时已经把所有的陆地都填充到队列中了，挨个遍历陆地，向外扩散一层
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        //默认距离为-1，是因为题意说了，如果不存在可扩展就是返回-1
        int dis = -1;
        if (queue.isEmpty() || queue.size() == n * n) return dis;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] temp = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = temp[0] + dx[k];
                    int y = temp[1] + dy[k];
                    if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        queue.add(new int[]{x, y});
                    }
                }
            }
            //我们这边默认每次层遍历结果都添加一位，但需要考虑到最后一次遍历的时候，
            // 整个都是大陆，原先结果需要-1，但因为初始值为-1，所以就不用了
            dis ++;
        }
        return dis;
    }
}
