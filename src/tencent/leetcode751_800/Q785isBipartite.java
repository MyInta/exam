package tencent.leetcode751_800;

/**
 * @author inta
 * @date 2020/2/5
 * @describe 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，
 * 一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。
 * 每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 *
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 *
 */
public class Q785isBipartite {
    //看了题解才明白是什么意思，就是从任何一个点开始，标记起始点颜色1，层次遍历与该点连接的点，标记点为颜色2
    //每进一步遍历都标记下，遍历到与起始点颜色不一样的返回false，否则染上与起始点不一样的颜色
    public boolean isBipartite(int[][] graph) {
        //有多少个点
        int size = graph.length;
        //标记点(索引为-1)是否遍历过 0表示没访问过，1表示访问过了颜色1，2表示颜色2
        int[] visited = new int[size];
        for (int i = 0; i < size; i ++) {
            //如果遍历发现返回false，即遍历到已经遍历过的，就说明非二分图
            if (!dfs(graph, visited, i, 0)) return false;
        }
        return true;
    }
    //start为起始点，lastColor为标记遍历之前点的颜色
    private boolean dfs(int[][] graph, int[] visited, int start, int lastColor) {
        //如果点颜色已经被染过，就不深入下去了，因为其关联点肯定都被染过,返回判断它是否和上一个结点颜色相同
        if (visited[start] != 0) return visited[start] != lastColor;
        //否则说明没染过，染成和前一个点颜色不一样的
        visited[start] = lastColor == 1 ? 2 : 1;
        //获得start索引对应其他点信息
        int[] temp = graph[start];
        //这些点都要标记访问过了
        for (int t : temp) {
            //每次深入找下一个结点的关联点时候，lastColor为父结点颜色
            if (!dfs(graph, visited, t, visited[start])) return false;
        }
        return true;
    }
}
