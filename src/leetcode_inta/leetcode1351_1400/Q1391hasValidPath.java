package leetcode_inta.leetcode1351_1400;

import java.util.*;

/**
 * @author inta
 * @date 2020/5/22
 * @describe 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 *
 *     1 表示连接左单元格和右单元格的街道。
 *     2 表示连接上单元格和下单元格的街道。
 *     3 表示连接左单元格和下单元格的街道。
 *     4 表示连接右单元格和下单元格的街道。
 *     5 表示连接左单元格和上单元格的街道。
 *     6 表示连接右单元格和上单元格的街道。
 *
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、
 * 一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
 *
 * 注意：你 不能 变更街道。
 *
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[2,4,3],[6,5,2]]
 * 输出：true
 * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 *
 * 示例 2：
 *
 * 输入：grid = [[1,2,1],[1,2,1]]
 * 输出：false
 * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 *
 * 示例 3：
 *
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 *
 * 示例 4：
 *
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 *
 * 示例 5：
 *
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 *
 *
 *
 * 提示：
 *
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 300
 *     1 <= grid[i][j] <= 6
 *
 */
public class Q1391hasValidPath {
    //dfs
    public boolean hasValidPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //方向数组，分别对应左、上、右、下
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        //存储当前遍历到的节点
        Queue<Pair> queue = new LinkedList<>();
        //用一个set保存遍历过的节点
        boolean[][] visited = new boolean[n][m];
        Pair p = new Pair(0, 0);
        queue.add(p);
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Pair temp = queue.poll();
            int key = temp.key;
            int value = temp.value;
            //当走通时，直接返回true即可
            if (key == n - 1 && value == m - 1) return true;
            //否则开始考虑走位,4个方向
            for (int i = 0; i < 4; i++) {
                int destX = key + dx[i];
                int destY = value + dy[i];
                Pair next = new Pair(destX, destY);
                //越界不取
                if (destX < 0 || destX >= n || destY < 0 || destY >= m || visited[destX][destY]) continue;
                //如果该路，走得通，并且是没有访问过的，就保存目标点
                if (check(grid[key][value], grid[destX][destY], i)) {
                    queue.add(next);
                    visited[destX][destY] = true;
                }
            }
        }
        return false;
    }

    private class Pair{
        int key;
        int value;
        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    //检验目标位置，目的地，以及方向标志三者共存时，是否符合题意
    private boolean check(int from, int dest, int redi) {
        //向左
        if (redi == 0) {
            //from来源于可以向左的街道，dest去往可以接受右方来的街道，下面同理
            if ((from == 1 || from == 3 || from == 5) && (dest == 1 || dest == 4 || dest == 6)) {
                return true;
            }
        }
        //向上
        if (redi == 1) {
            if ((from == 2 || from == 5 || from == 6) && (dest == 2 || dest == 3 || dest == 4)) {
                return true;
            }
        }
        //向右
        if (redi == 2) {
            if ((from == 1 || from == 4 || from == 6) && (dest == 1 || dest == 3 || dest == 5)) {
                return true;
            }
        }
        //向下
        if (redi == 3) {
            if ((from == 2 || from == 3 || from == 4) && (dest == 2 || dest == 5 || dest == 6)) {
                return true;
            }
        }
        return false;
    }
}
