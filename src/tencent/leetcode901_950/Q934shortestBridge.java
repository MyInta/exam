package tencent.leetcode901_950;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/11/5
 * @describe 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 *
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 *
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 *
 * 输入：[[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 *
 * 输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 */
public class Q934shortestBridge {
    //思路，第一次dfs找到一个岛屿所有位置，并保存，然后遍历所有位置以进行bfs搜索能否触及另一个岛屿
    public int shortestBridge(int[][] A) {
        //题意得，其为nxn得矩阵
        int n = A.length;
        int[][] ways = {{0,-1}, {-1,0}, {0,1}, {1,0}};
        //用来判断是否访问过的
        boolean[][] visited = new boolean[n][n];
        //用来存储第一个岛的信息，以bfs
        Queue<int[]> queue = new LinkedList<>();
        //标签用来找到第一个岛之后，直接跳出“大”的循环
        firstLoop:
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (A[i][j] == 1) {
                    dfs(visited, A, i, j, ways, queue);
                    //跳出大循环
                    break firstLoop;
                }
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                int[] temp = queue.poll();
                for (int[] way : ways) {
                    int x_i = way[0] + temp[0];
                    int y_j = way[1] + temp[1];
                    if (x_i < 0 || x_i >= n || y_j < 0 || y_j >= n || visited[x_i][y_j]) continue;
                    //如果碰到为1的，而且是未访问的，说明是新岛，即到达目标，直接返回
                    if (A[x_i][y_j] == 1) return res;
                    //标记访过的点
                    visited[x_i][y_j] = true;
                    //并且把这个0的河面变成桥，即加入到队列中准备下一波的延伸
                    queue.offer(new int[]{x_i, y_j});
                }
            }
            //每找完一圈之后，相当于桥往外延伸了一圈
            res ++;
        }
        return res;
    }
    private void dfs(boolean[][] visited, int[][] A, int i, int j, int[][] ways, Queue<int[]> queue) {
        int n = visited.length;
        //越界或者是访问过得就直接返回
        if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || A[i][j] == 0) return;
        //现在该点也被承包为true了
        visited[i][j] = true;
        //并且该点因为不为0，我们将其加入到准备bfs得队列中
        queue.offer(new int[]{i, j});
        for (int[] way : ways) {
            int x_i = i + way[0];
            int y_j = j + way[1];
            dfs(visited, A, x_i, y_j, ways, queue);
        }
    }

}
