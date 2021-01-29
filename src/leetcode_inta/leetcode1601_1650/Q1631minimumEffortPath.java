package leetcode_inta.leetcode1601_1650;

import com.sun.javafx.geom.Edge;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/29
 * @describe 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
 * 其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，
 * 且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 示例 1：
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *  
 * 提示：
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 */
public class Q1631minimumEffortPath {
    // 一开始做成dp题，发现考虑上下左右四个方向不好写状态转移，
    // 看评论区才意识到是并查集，但也不知道怎么实现，又看了一遍思路才知道怎么解，路还很长，加油！
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = i * cols + j;
                if (i > 0) {
                    int dis = Math.abs(heights[i][j] - heights[i - 1][j]);
                    int y = (i - 1) * cols + j;
                    edges.add(new int[]{dis, x, y});
                }
                if (j > 0) {
                    int dis = Math.abs(heights[i][j] - heights[i][j - 1]);
                    int y = i * cols + j - 1;
                    edges.add(new int[]{dis, x, y});
                }
            }
        }

        Collections.sort(edges, Comparator.comparing(edge->edge[0]));
        int n = rows * cols;
        int max = 0;
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (unionFind.isUnion(0, n - 1)) {
                break;
            }
            unionFind.union(edge[1], edge[2]);
            max = Math.max(max, edge[0]);
        }
        return max;
    }

    private class UnionFind {
        int[] parents;

        public UnionFind(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public boolean isUnion(int x, int y) {
            return find(x) == find(y);
        }

        private int find(int child) {
            if (parents[child] != child) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }

        private void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return;
            }
            parents[parentX] = parentY;
        }
    }
}
