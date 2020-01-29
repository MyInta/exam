package tencent.leetcode401_450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/1/29
 * @describe 给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，
 * 请计算图中形状最多能接多少体积的雨水。
 *
 * 说明:
 *
 * m 和 n 都是小于110的整数。每一个单位的高度都大于 0 且小于 20000。
 * 示例：
 *
 * 给出如下 3x6 的高度图:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 *
 * 返回 4。
 *
 * [9,9,9,9,9,9,8,9,9,9,9],
 * [9,0,0,0,0,0,1,0,0,0,9],
 * [9,0,0,0,0,0,0,0,0,0,9],
 * [9,0,0,0,0,0,0,0,0,0,9],
 * [9,9,9,9,9,9,9,9,9,9,9]
 *
 * 如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
 *
 * 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
 *
 */
public class Q407trapRainWater {
    //网友提供了一个思路，就是一层层内套，想象成由一个个木桶嵌套，
    // 每次遍历外面一层，找最短的木板，下一层可接雨水为差值，更新一圈下来的木板最短值，并以内圈为外圈迭代
    private class Pair{
        int x;
        int y;
        int h;
        Pair(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 3 || heightMap[0].length < 3) return 0;
        int n = heightMap.length;
        int m = heightMap[0].length;
        //记录是否遍历过，为1表示遍历过的，为0表示没有遍历过，默认值0
        int[][] dp = new int[n][m];
        //遍历最外圈，将信息填充，每个格子信息保存到优先队列中，以高度最小优先排序
        PriorityQueue<Pair> p = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                //以高度最小优先排序
                return o1.h - o2.h;
            }
        });
        //遍历外圈
        for (int i = 0; i < n; i ++) {
            //遍历过的元素都赋值1，表示遍历过了
            dp[i][0] = 1;
            dp[i][m - 1] = 1;
            //添加每个格子信息到优先队列
            p.add(new Pair(i, 0, heightMap[i][0]));
            p.add(new Pair(i, m - 1, heightMap[i][m - 1]));
        }
        //注意这里因为左右两列信息已添加，所以不要再重复那四个元素了
        for (int j = 1; j < m - 1; j ++) {
            //遍历过的元素都赋值1，表示遍历过了
            dp[0][j] = 1;
            dp[n - 1][j] = 1;
            //添加每个格子信息到优先队列
            p.add(new Pair(0, j, heightMap[0][j]));
            p.add(new Pair(n - 1, j, heightMap[n - 1][j]));
        }
        //雨水量
        int res = 0;
        //目前已经把外圈信息都查询和添加过了，开始对所有队列木板高度的元素遍历
        while (!p.isEmpty()) {
            //取出高度最小的木板
            Pair temp = p.poll();
            //分别取出坐标和高
            int x = temp.x;
            int y = temp.y;
            int h = temp.h;
            //查询木板临近四周格子信息
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : directions) {
                //如果越界过着访问过，就找另一个方向邻近格子
                int dx = x + dir[0];
                int dy = y + dir[1];
                if (dx < 0 || dx >= n || dy < 0 || dy >= m || dp[dx][dy] == 1) continue;
                //如果取出的木板高度比遍历点高，差值为雨水量，更新木板信息并保存进去
                if (h > heightMap[dx][dy]) {
                    res += (h - heightMap[dx][dy]);
                }
                //把这个之前没遍历过的木板信息添加到队列中，注意高度刷新为较大值
                p.add(new Pair(dx, dy, Math.max(h, heightMap[dx][dy])));
                //并标记为访问过了的
                dp[dx][dy] = 1;
            }
        }
        return res;
    }
}
