package leetcode_inta.leetcode1451_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2021/1/21
 * @describe 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
 * 其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
 * 最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，
 * 那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 * 示例 1：
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：上图描述了给定图。
 * 下图是所有的最小生成树。
 *
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
 * 示例 2 ：
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * 所有 (fromi, toi) 数对都是互不相同的。
 */
public class Q1489findCriticalAndPseudoCriticalEdges {
    // 看了官方题解才有思路，还是太菜了，按照kruskal算法思想(内涵并查集作工具)分三步走
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 预处理，用于后续定位边的编号
        int m = edges.length;
        int[][] preEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                preEdges[i][j] = edges[i][j];
            }
            preEdges[i][3] = i; // 比edges多了一列以记录该边原先的索引编号
        }

        // 按边的权重升序排序，此时打乱了原有编号，故上面需要一列记录编号值
        Arrays.sort(preEdges, (preEdgeOne, preEdgeTwo) -> preEdgeOne[2] - preEdgeTwo[2]);

        // 第一步：找到最小树权重和
        UnionFind unionFind = new UnionFind(n);
        int minValue = 0;
        for (int i = 0; i < m; i++) { // 题意说没有别的点存在，可以放心merge，当n点连通后，后续merge都为false
            if (unionFind.merge(preEdges[i][0], preEdges[i][1])) {
                minValue += preEdges[i][2];
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            // 第二步：遍历剔除每条边时求最小树，判断是否为关键边
            unionFind = new UnionFind(n);
            int value = 0;
            for (int j = 0; j < m; j++) {
                if (i != j && unionFind.merge(preEdges[j][0], preEdges[j][1])) {
                    value += preEdges[j][2];
                }
            }
            // 判断是否形成一颗树，且值和最小值匹配
            if (unionFind.getCount() != 1 || (unionFind.getCount() == 1 && value > minValue)) {
                res.get(0).add(preEdges[i][3]);
                continue; // 说明是关键边，那就不需要判断是否伪边，直接继续
            }

            // 第三步：遍历考虑取每条边时，判断是否为伪关键边
            unionFind = new UnionFind(n);
            unionFind.merge(preEdges[i][0], preEdges[i][1]);
            value = preEdges[i][2]; // 直接取该边，并添加其权重
            for (int j = 0; j < m; j++) {
                if (i != j && unionFind.merge(preEdges[j][0], preEdges[j][1])) {
                    value += preEdges[j][2];
                }
            }
            // 判断是否形成树(不用判断，前面关键边已剔除该可能)，且值和最小树一致，=
            if (value == minValue) {
                res.get(1).add(preEdges[i][3]);
            }
        }
        return res;
    }

    /**
     * 并查集工具类，主要提供点与点归并、返回生成树数量的作用
     */
    private class UnionFind {
        private int[] parents;
        private int count; // 记录最终形成了多少棵树

        public UnionFind(int count) {
            this.count = count;
            this.parents = new int[count];
            for (int i = 0; i < count; i++) {
                parents[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        // 合并两点，返回能否合并
        public boolean merge(int x, int y) {
            int findX = find(x);
            int findY = find(y);
            if (findX == findY) {
                return false;
            }
            parents[findX] = findY;
            count--;
            return true;
        }

        // 查找根节点
        public int find(int child) {
            if (parents[child] != child) {
                parents[child] = find(parents[child]);
            }
            return parents[child];
        }
    }
}
