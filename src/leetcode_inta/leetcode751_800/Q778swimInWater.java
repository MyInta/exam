package leetcode_inta.leetcode751_800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author inta
 * @date 2021/1/30
 * @describe 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。
 * 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
 * 假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * 示例 1:
 * 输入: [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，
 * 所以你可以游向坐标方格中的任意位置
 * 示例2:
 *
 * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 输出: 16
 * 解释:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * 最终的路线用加粗进行了标记。
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 * 提示:
 * 2 <= N <= 50.
 * grid[i][j] 是 [0, ..., N*N - 1] 的排列。
 */
public class Q778swimInWater {
    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = i * cols + j;
                if (i > 0) {
                    int dis = Math.max(grid[i][j], grid[i - 1][j]);
                    int y = (i - 1) * cols + j;
                    edges.add(new int[]{dis, x, y});
                }
                if (j > 0) {
                    int dis = Math.max(grid[i][j], grid[i][j - 1]);
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

