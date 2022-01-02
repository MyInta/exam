package leetcode_inta.exam_main.leetcode_exam2021.D67;

import java.util.*;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q3 {
    // 判断炸弹引爆牵连最多的数量,给每个炸弹找他们可以牵连的炸弹
    public int maximumDetonation(int[][] bombs) {
        // 炸弹牵连炸弹集合
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < bombs.length; j++) {
                if (i == j) {
                    continue;
                }
                long difX = bombs[i][0] - bombs[j][0];
                long difY = bombs[i][1] - bombs[j][1];
                if (difX * difX + difY * difY <= (long)bombs[i][2] * bombs[i][2]) {
                    list.add(j);
                }
            }
            map.put(i, list);
        }
        // 各个炸弹能引爆的我们都找到了，剩下的就是挨个选择来看看dfs下数量最大情况
        int res = 0;
        for (int i = 0; i < bombs.length; i++) {
            res = Math.max(res, dfs(i, map));
        }
        return res;
    }

    private int dfs(int index, Map<Integer, List<Integer>> map) {
        // 记录已经访问过的
        int count = 1;
        Set<Integer> set = new HashSet<>();
        set.add(index);
        List<Integer> curList = map.getOrDefault(index, new LinkedList<>());
        Deque<Integer> deque = new LinkedList<>();
        for (int num : curList) {
            deque.add(num);
            set.add(num);
            count++;
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int curIndex = deque.poll();
                List<Integer> tempList = map.getOrDefault(curIndex, new LinkedList<>());
                for (int num : tempList) {
                    if (!set.contains(num)) {
                        deque.add(num);
                        set.add(num);
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
