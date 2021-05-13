package leetcode_inta.leetcode851_900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2021/5/10
 * @describe 给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
 * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
 * 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 * 示例 1：
 * 输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 * 示例 2：
 * 输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 * 示例 3：
 * 输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 * 提示：
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * 对于 dislikes[i] == dislikes[j] 不存在 i != j
 */
public class Q886possibleBipartition {
    // 邻接表+上色 dfs上色过程即可
    private Map<Integer, Set<Integer>> map;
    private Map<Integer, Integer> colors;
    public boolean possibleBipartition(int N, int[][] dislikes) {
        // 邻接表，记录人与人之间的喜好关系
        map = new HashMap<>();
        // 创建着色记录，记录人员标号和颜色
        colors = new HashMap<>();
        // 邻接表初始化，将所有成员间关系建立
        for (int[] dis : dislikes) {
            Set<Integer> temp1 = map.getOrDefault(dis[0], new HashSet<>());
            temp1.add(dis[1]);
            map.put(dis[0], temp1);
            Set<Integer> temp2 = map.getOrDefault(dis[1], new HashSet<>());
            temp2.add(dis[0]);
            map.put(dis[1], temp2);
        }
        for (int i = 1; i <= N; i++) {
            if (!colors.containsKey(i) && !dfs(i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int i, int color) {
        if (colors.containsKey(i)) {
            return colors.get(i).equals(color);
        }
        // 着色
        colors.put(i, color);
        // 遍历其关联对象进行另一个着色
        for (Integer other : map.getOrDefault(i, new HashSet<>())) {
            if (!dfs(other, color ^ 1)) {
                return false;
            }
        }
        return true;
    }

}
