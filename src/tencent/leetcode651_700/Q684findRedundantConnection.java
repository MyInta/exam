package tencent.leetcode651_700;

/**
 * @author inta
 * @date 2020/1/24
 * @describe 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * 注意:
 *
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * 更新(2017-09-26):
 * 我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
 *
 */
public class Q684findRedundantConnection {

    private int[] pre;
    public int[] findRedundantConnection(int[][] edges) {
        //初始化，将各个帮派领导都是自己
        pre = new int[1001];
        for (int i = 0; i < 1001; i ++) {
            pre[i] = i;
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            //找他们的帮主，如果一样，就是我们需要破坏的多余的链接
            if (find(x) == find(y)) return edge;
            merge(x, y);
        }
        //如果没找到，没提前返回，就默认返回null
        return new int[0];
    }
    //找到它的帮主,还需要压缩路径节省时间
    private int find(int n) {
        //分别记录帮派下面的兄弟，和临时储存的他的一级上级
        int child = n, temp;
        while (n != pre[n]) {
            n = pre[n];
        }
        //此时的n已经是帮派主了，当其一级上级不为boss之前，我们都压缩路径使其指向boss
        while (child != n) {
            //保存其上级
            temp = pre[child];
            //指向boss
            pre[child] = n;
            child = temp;
        }
        return n;
    }
    //合并帮派
    private void merge(int a, int b) {
        //a b两帮派合并 根据题意知我们这边默认假设需要合并的是帮派主不相同的
        pre[find(a)] = find(b);
    }
}
