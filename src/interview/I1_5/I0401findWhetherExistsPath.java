package interview.I1_5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/25
 * @describe 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 *
 * 示例1:
 *
 *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 *  输出：true
 *
 * 示例2:
 *
 *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]],
 *  start = 0, target = 4
 *  输出 true
 *
 * 提示：
 *
 *     节点数量n在[0, 1e5]范围内。
 *     节点编号大于等于 0 小于 n。
 *     图中可能存在自环和平行边。
 *
 */
public class I0401findWhetherExistsPath {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        //设一个布尔值来记录访问过的结点
        visited = new boolean[n];
        //数组集合保存每个结点映射的各个结点信息
        lists = new ArrayList[n];
        //建立结点关系 a->{b,c,d,e,f}
        for (int[] g : graph) {
            //第一次的结点，新建数组用于存储
            if (lists[g[0]] == null) lists[g[0]] = new ArrayList<>();
            lists[g[0]].add(g[1]);
        }
        return dfs(start, target);
    }
    private boolean[] visited;
    private List<Integer>[] lists;
    private boolean dfs(int start, int target) {
        if (start == target) return true;
        //标记访问过
        visited[start] = true;
        List<Integer> next = lists[start];
        //如果该结点后续没有结点集合，说明走不通，直接返回false
        if (next == null) return false;
        //遍历若存在的该结点关联的所有结点，来寻找下一个关系
        for (int i : next) {
            //如果该结点我们早就遍历过就跳过
            if (visited[i]) continue;
            if (dfs(i, target)) return true;
        }
        return false;
    }
}
