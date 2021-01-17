package leetcode_inta.leetcode1201_1250;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/12
 * @describe 公司共有 n 个项目和  m 个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。
 * group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1。
 * （项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。
 * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
 *
 * 同一小组的项目，排序后在列表中彼此相邻。
 * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，
 * 其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。
 * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。
 * 示例 1：
 *
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * 输出：[6,3,4,1,5,2,0,7]
 * 示例 2：
 *
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * 输出：[]
 * 解释：与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
 *
 * 提示：
 *
 * 1 <= m <= n <= 3 * 104
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m - 1
 * 0 <= beforeItems[i].length <= n - 1
 * 0 <= beforeItems[i][j] <= n - 1
 * i != beforeItems[i][j]
 * beforeItems[i] 不含重复元素
 */
public class Q1203sortItems {
    // 第一次使用邻接表做拓扑排序困难题，思路延续的官解，具体细节自己尝试来实现
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 先把没有组接管的项目设置为新的组管理
        int groupNumber = m;

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupNumber++; // 设置未被管理的项目为新的组，之后把组序号加一
            }
        }

        // 获取组的邻接表和项目的邻接表
        List<Integer>[] groupAdj = new ArrayList[groupNumber];
        List<Integer>[] itemAdj = new ArrayList[n];

        for (int i = 0; i < groupNumber; i++) {
            groupAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }

        // 以及组与项目的入度表
        int[] groupDegree = new int[groupNumber];
        int[] itemDegree = new int[n];

        for (int i = 0; i < n; i++) {
            int curGroup = group[i]; // 当前组号

            for (Integer beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (curGroup != beforeGroup) { // 如果是不相同的组，就建立邻接表关系
                    groupAdj[beforeGroup].add(curGroup);
                    groupDegree[curGroup]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer beforeItem : beforeItems.get(i)) {
                itemAdj[beforeItem].add(i);
                itemDegree[i]++;
            }
        }

        // 对组和项目进行拓扑排序
        List<Integer> topoGroup = topoSort(groupAdj, groupDegree, groupNumber);
        List<Integer> topoItem = topoSort(itemAdj, itemDegree, n);
        if (topoGroup.size() == 0 || topoItem.size() == 0) { // 若不存在合理的拓扑排序就说明不符合题意的构成
            return new int[0];
        }

        // 构建组与项目的一对多映射关系
        Map<Integer, List<Integer>> group2Item = new HashMap<>();
        for (Integer item : topoItem) {
            group2Item.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        // 往拓扑排序好的组中按照项目拓扑排序进行植入，获得最终答案
        List<Integer> res = new ArrayList<>();
        for (Integer tpGroup : topoGroup) {
            List<Integer> items = group2Item.getOrDefault(tpGroup, new ArrayList<>());
            res.addAll(items);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     *  拓扑排序
     * @param adj 邻接表
     * @param degrees 入读表
     * @param nodes 节点数
     * @return 返回拓扑排序后的节点列表，如果不构成拓扑排序返回空列表
     */
    private List<Integer> topoSort(List<Integer>[] adj, int[] degrees, int nodes) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) { // 如果入度为零，添加到队列中，准备作为一开始遍历的层级所拥有的节点
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer temp = queue.poll();
            res.add(temp);
            for (Integer next : adj[temp]) { // 通过邻接表遍历该点后需要连接的节点，进行出度
                degrees[next]--;
                if (degrees[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (res.size() == nodes) { // 如果拓扑排序存在，即可以获取节点数一致的排序列表，否则返回空集合
            return res;
        }
        return new ArrayList<>();
    }
}
