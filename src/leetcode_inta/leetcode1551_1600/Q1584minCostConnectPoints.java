package leetcode_inta.leetcode1551_1600;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/19
 * @describe 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * 示例 1：
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 */
public class Q1584minCostConnectPoints {
    // 思想，找到每个点可以连接的最短路径，并且把该连过的两点看作一个整体，其他点要取最短距离的前提是得和整体能连通
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0] - b[0]);
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dis = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pq.add(new int[]{dis, i, j});
            }
        }

        int res = 0;
        UnionFind unionFind = new UnionFind(points.length);
        for (int i = 0; i < points.length - 1; i++) {
            int[] cur = pq.poll();
            if (unionFind.find(cur[1]) == unionFind.find(cur[2])) {
                continue;
            }
            unionFind.merge(cur[1], cur[2]);
            res += cur[0];
        }
        return res;
    }

    private class UnionFind {
        int[] parents;

        public UnionFind(int number) {
            this.parents = new int[number];
            Arrays.fill(parents, -1);
        }

        public void merge(int x, int y) {
            int parentsX = find(x);
            int parentsY = find(y);
            parents[parentsX] = parentsY;
        }

        public int find(int child) {
            if (parents[child] == -1) {
                return child;
            }
            return find(parents[child]);
        }
    }

    private int[] parents;
    public int minCostConnectPoints2(int[][] points) {
        int res = 0;
        int size = points.length;
        parents = new int[size];
        Arrays.fill(parents, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0] - b[0]);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                pq.add(new int[]{Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), i, j});
            }
        }
        int rang = 0;
        while (rang < size - 1) {
            int[] temp = pq.poll();
            int x_p = findP(temp[1]);
            int y_p = findP(temp[2]);
            if (x_p != y_p) {
                res += temp[0];
                parents[x_p] = y_p;
                rang ++;
            }

        }
        return res;
    }
    private int findP(int i) {
        if (parents[i] == -1) return i;
        return findP(parents[i]);
    }
}
