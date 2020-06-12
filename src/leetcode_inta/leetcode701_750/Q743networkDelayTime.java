package leetcode_inta.leetcode701_750;

import java.util.*;

/**
 * @author inta
 * @date 2020/6/10
 * @describe 有 N 个网络节点，标记为 1 到 N。
 *
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，
 * 其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 *
 *
 *
 * 示例：
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * 输出：2
 *
 *
 *
 * 注意:
 *
 *     N 的范围在 [1, 100] 之间。
 *     K 的范围在 [1, N] 之间。
 *     times 的长度在 [1, 6000] 之间。
 *     所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。
 *
 */
public class Q743networkDelayTime {
    //Dijstra算法
    public int networkDelayTime(int[][] times, int N, int K) {
        //二维数组保存i->j的距离
        int[][] dis = new int[N + 1][N + 1];
        for (int[] d : dis) {
            Arrays.fill(d, -1);
        }
        //填充真实距离
        for (int[] time : times) {
            dis[time[0]][time[1]] = time[2];
        }
        //使用一个数组保存访问到i节点最短距离
        int[] count = new int[N + 1];
        Arrays.fill(count, -1);
        //从真实距离中开始填充访问到i节点的数组
        for (int i = 1; i <= N; i++) {
            count[i] = dis[K][i];
        }
        //默认K开始，所以K的最短距离为0
        count[K] = 0;
        //使用一个布尔数组保存是否访问过该节点，同上K访问过了,+1是方便节点与索引对应
        boolean[] visited = new boolean[N + 1];
        visited[K] = true;
        //访问所有非K的节点(N - 1个)，遍历看K节点到这些节点是否有路径
        for (int i = 1; i < N; i++) {
            //记录离上一节点最近的节点信息
            int index = i;
            //记录最近那个节点的距离
            int distance = Integer.MAX_VALUE;
            //从N节点中找没被遍历过，并且离上一节点距离最近的
            for (int j = 1; j <= N; j++) {
                //找到一个没有访问过，存在距离,并且比较后更短的
                if (!visited[j] && count[j] != -1 && count[j] < distance) {
                    //更新更短的
                    distance = count[j];
                    //保留节点信息
                    index = j;
                }
            }
            //遍历一圈，找到未访问过并且距离K最短的节点
            // （因为所有节点都是相对于K节点比较距离，换言之每轮比较比一个节点更远一点的节点）
            visited[index] = true;
            //依据这个节点更新所有最短距离(从该节点到下一个节点的距离)
            for (int j = 1; j <= N; j++) {
                //该节点到j节点有路，就刷新掉
                if (dis[index][j] != -1) {
                    //如果k到j本来就有直通车，刷新直通车距离为比较拼接距离后的最短
                    if (count[j] != -1) {
                        count[j] = Math.min(count[j], count[index] + dis[index][j]);
                    } else {
                        //否则直接更新就可以
                        count[j] = count[index] + dis[index][j];
                    }
                }
            }
            //至此，一轮下来，我们找到了相对K节点最近的“又”一个未被访问过的点，并依据该点可以传播的方式
            //刷新了所有该点可传播到的节点对应count数组内保留的K到该节点的路径距离为可能的“直通车”与拼接路径后的最短距离
        }
        //那么我们遍历count保留最短路径距离的数组，如果有-1说明该处节点没被访问到，不符合题意,
        // 选出到各点路径最短中的最长路径，就是访问点最多的情况时经过的最短路径
        int res = 0;
        //被填充过，0节点不存在，-1，排除掉这个干扰项
        count[0] = 0;
        for (int c : count) {
            if (c == -1) return -1;
            if (c > res) res = c;
        }
        return res;
    }
}
