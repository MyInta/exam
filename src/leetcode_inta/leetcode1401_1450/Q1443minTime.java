package leetcode_inta.leetcode1401_1450;

import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/6/13
 * @describe 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。
 * 通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 *
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。
 * 除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * 输出：8
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 *
 * 示例 2：
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * 输出：6
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 *
 * 示例 3：
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * 输出：0
 *
 *
 *
 * 提示：
 *
 *     1 <= n <= 10^5
 *     edges.length == n-1
 *     edges[i].length == 2
 *     0 <= fromi, toi <= n-1
 *     fromi < toi
 *     hasApple.length == n
 */
public class Q1443minTime {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        //记录有苹果的路径上节点个数
        int count = 0;
        //记录访问过的节点
        boolean[] visited = new boolean[n];
        //构建下节点与节点间的关系，子到父
        for (int[] edge : edges) {
            //考虑到将要成为父节点的父节点如果不是题意要求的0，就是另外一颗树的
            if (findP(parents, edge[1]) != 0) {
                //那么我们可以认为这个父节点应该反过来依附于子节点
                parents[edge[1]] = edge[0];
            } else {
                parents[edge[0]] = edge[1];
            }
        }
        //从头开始遍历
        visited[0] = true;
        for (int i = 1; i < n; i++) {
            //如果当前节点是苹果，那么它的父节点应该都是可以充当路径的节点
            if (hasApple.get(i)) {
                int temp = i;
                while (!visited[temp]) {
                    visited[temp] = true;
                    count ++;
                    temp = parents[temp];
                }
            }
        }

        //路径乘以二
        return count << 1;
    }
    //找父节点
    private int findP(int[] parents, int target) {
        if (parents[target] == -1) return -1;
        if (parents[target] == 0) return 0;
        return findP(parents, parents[target]);
    }
}
