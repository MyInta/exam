package tencent.exam_main.leetcode_exam2020.D179;

import java.util.*;

/**
 * @author inta
 * @date 2020/3/8
 * @describe
 */
public class Q3 {
    //先建立互相之间关系map<Integer, List>
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        res = informTime[headID];
        for (int i = 0; i < n; i ++) {
            map.put(i, new HashMap<>());
        }
        for (int i = 0; i < n; i ++) {
            int boss = manager[i], employee = i, time = informTime[i];
            if (boss == -1) continue;
            map.get(boss).put(employee, time);
        }
        res += dfs(map, headID);
        return res;
    }
    private int res;
    private int dfs(Map<Integer, Map<Integer, Integer>> map, int index) {
        int ans = 0;
        for (int next : map.get(index).keySet()) {
            ans = Math.max(ans, map.get(index).get(next) + dfs(map, next));
        }
        return ans;
    }

}
