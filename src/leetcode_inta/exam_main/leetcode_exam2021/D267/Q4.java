package leetcode_inta.exam_main.leetcode_exam2021.D267;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2021/11/14
 * @describe
 */
public class Q4 {
    // 并查集思想，res中的元素各自为其父节点，而requests一开始建立关系如果发现两者不是一个帮派，就吹了，且给他新建个帮派
    private Map<Integer, Integer> parents;
    // 历史遗留帮派都是老顽固，不让合并
    private Map<Integer, Set<Integer>> visited;
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        parents = new HashMap<>();
        visited = new HashMap<>();
        for (int[] restriction : restrictions) {
            // 相信题以的输入，不会出现奇怪的点
            if (!parents.containsKey(restriction[0])) {
                Set<Integer> tempSet = visited.getOrDefault(restriction[0], new HashSet<>());
                tempSet.add(restriction[1]);
                visited.put(restriction[0], tempSet);
                parents.put(restriction[0], restriction[0]);
            }
            if (!parents.containsKey(restriction[1])) {
                Set<Integer> tempSet = visited.getOrDefault(restriction[1], new HashSet<>());
                tempSet.add(restriction[0]);
                visited.put(restriction[1], tempSet);
                parents.put(restriction[1], restriction[1]);
            }
        }
        boolean[] res = new boolean[requests.length];
        for (int i = 0; i < res.length; i++) {
            int[] resq = requests[i];
            if (!parents.containsKey(resq[0]) && !parents.containsKey(resq[1])) {
                res[i] = true;
                parents.put(resq[0], resq[0]);
                parents.put(resq[1], resq[0]);
            } else if (!parents.containsKey(resq[0])) {
                res[i] = true;
                parents.put(resq[0], findParent(resq[1]));
            } else if (!parents.containsKey(resq[1])) {
                res[i] = true;
                parents.put(resq[1], findParent(resq[0]));
            } else {
                int pA = findParent(resq[0]);
                int pB = findParent(resq[1]);
                if (visited.containsKey(pA) && visited.get(pA).contains(pB)) {
                    res[i] = false;
                    continue;
                }
                if (visited.containsKey(pB) && visited.get(pB).contains(pA)) {
                    res[i] = false;
                    continue;
                }
                if (visited.containsKey(pA)) {
                    parents.put(pB, pA);
                } else {
                    parents.put(pA, pB);
                }
                res[i] = true;
            }
        }
        return res;
    }

    private int findParent(int child) {
        if (parents.get(child) != child) {
            parents.put(parents.get(child), findParent(parents.get(child)));
        }
        return parents.get(child);
    }
}
