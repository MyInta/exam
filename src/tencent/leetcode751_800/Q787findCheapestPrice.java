package tencent.leetcode751_800;

import java.util.*;

/**
 * @author inta
 * @date 2019/11/2
 * @describe 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
 *
 * 示例 1:
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200
 *
 * 示例 2:
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 *从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500
 * 提示：
 *
 * n 范围是 [1, 100]，城市标签从 0 到 n - 1.
 * 航班数量范围是 [0, n * (n - 1) / 2].
 * 每个航班的格式 (src, dst, price).
 * 每个航班的价格范围是 [1, 10000].
 * k 范围是 [0, n - 1].
 * 航班没有重复，且不存在环路
 *
 */
public class Q787findCheapestPrice {
    //思路：从起点开枝散叶，宽度遍历（深度限定在K内），最后找到终点，返回每一支脉最小值
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //map保存起点和hashmap，hashmap内保存终点和费用
        Map<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            if (map.containsKey(flight[0])) {
//                HashMap hm = map.get(flight[0]);
//                hm.put(flight[1], flight[2]);
//                map.put(flight[0], hm);
                map.get(flight[0]).put(flight[1], flight[2]);
            } else {
                HashMap new_hm = new HashMap();
                new_hm.put(flight[1], flight[2]);
                map.put(flight[0], new_hm);
            }
        }

        //优先队列里面保存 中转次数k 目的地 以及费用
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //费用排序,顶端最小
                return o1[2] - o2[2];
            }
        });

        priorityQueue.add(new int[]{0, src, 0});
        while (!priorityQueue.isEmpty()) {
            int[] temp = priorityQueue.poll();
            if (temp[1] == dst) {
                return temp[2];
            }
            if (temp[0] <= K) {
                //获得以该目的地为起始点的所有航班信息 hashmap里面保存了航班终点和费用
                //也有可能不存在该目的地为起点的航班信息，要设个默认值
                HashMap<Integer, Integer> hm = map.getOrDefault(temp[1], new HashMap<>());
                //全都加入到优先队列中，以费用排序
                for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
                    //每次添加，k都是+1，相当于中转了一次，然后费用是累加的
                    priorityQueue.add(new int[]{temp[0] + 1, entry.getKey(), temp[2] + entry.getValue()});
                }
            }
        }
        //如果上面没有返回值，说明不存在终点路线，或者是不满足K中转条件,返回-1
        return -1;
    }

    //使用动态规划 dp[i][k] 表示经过k个中转站 到达i需要的最小花费
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        //K如果为0，那也要有一个0元素存在，所以设K+1长度
        int[][] dp = new int[n][K + 1];
        //所有航班先设为费用最大化
        for (int i = 0; i < n; i ++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //考虑第一轮
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }
        //到达src即起始点的费用，自然为0（题目讲无闭环）
        for (int i = 0; i <= K; i ++) {
            dp[src][i] = 0;
        }
        for (int i = 1; i <= K; i ++) {
            for (int[] flight : flights) {
                //当到达该航班起始地的费用不是最大值时，说明是在从初始点开始的路线上
                if (dp[flight[0]][i - 1] != Integer.MAX_VALUE) {
                    dp[flight[1]][i] = Math.min(dp[flight[1]][i], dp[flight[0]][i - 1] + flight[2]);
                }
            }
        }

        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];
    }

}
