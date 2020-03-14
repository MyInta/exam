package leetcode_inta.leetcode1001_1050;

import java.util.*;

/**
 * @author inta
 * @date 2020/2/3
 * @describe 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 *
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 *
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1)
 * 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 *
 * 示例 1：
 *
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 *
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 *
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 *  
 *
 * 提示：
 *
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 */
public class Q1030allCellsDistOrder {
    //理解为先找r0行的c0 +- 1位置 再找 c0列的 r0 +- 1位置
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] allCells = new int[R * C][2];
        int index = 0;
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j ++) {
                allCells[index][0] = i;
                allCells[index][1] = j;
                index ++;
            }
        }
        Arrays.sort(allCells, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0) - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0);
            }
        });
        return allCells;
    }

    //网上大神的思路，理解为树的变形，四叉树，层次遍历bfs即可
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r0, c0});
        int[][] res = new int[R * C][2];
        //用来作为结果集中的索引
        int index = 0;
        //还需要考虑元素重复访问的情况
        boolean[][] visited = new boolean[R][C];
        //标记访问过
        visited[r0][c0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                int[] temp = queue.poll();
                int temp_x = temp[0];
                int temp_y = temp[1];
                //往结果集中添加
                res[index][0] = temp_x;
                res[index][1] = temp_y;
                index ++;
                //上
                if (temp_x + 1 < R && !visited[temp_x + 1][temp_y]) {
                    visited[temp_x + 1][temp_y] = true;
                    queue.add(new int[] {temp_x + 1, temp_y});
                }
                //右
                if (temp_y + 1 < C && !visited[temp_x][temp_y + 1]) {
                    visited[temp_x][temp_y + 1] = true;
                    queue.add(new int[] {temp_x, temp_y + 1});
                }
                //下
                if (temp_x - 1 >= 0 && !visited[temp_x - 1][temp_y]) {
                    visited[temp_x - 1][temp_y] = true;
                    queue.add(new int[] {temp_x - 1, temp_y});
                }
                //左
                if (temp_y - 1 >= 0 && !visited[temp_x][temp_y - 1]) {
                    visited[temp_x][temp_y - 1] = true;
                    queue.add(new int[] {temp_x, temp_y - 1});
                }
            }
        }
        return res;
    }
}
