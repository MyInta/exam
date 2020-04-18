package leetcode_inta.exam_main.personal_exam;

import java.util.*;

/**
 * @author inta
 * @date 2020/4/18
 * @describe
 */
public class Q2 {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] re : relation) {
            int key = re[0];
            int value = re[1];
            if (!map.containsKey(key)) map.put(key, new HashSet<>());
            map.get(key).add(value);
        }
        //此时获得了所有元素和对应直接关系表map
        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> nextLine;
        queue.add(0);
        while (k > 0) {
            nextLine = new LinkedList<>();
            while (!queue.isEmpty()) {
                int len = queue.size();
                for (int i = 0; i < len; i++) {
                    int temp = queue.poll();
                    if (map.containsKey(temp)) {
                        for (int next : map.get(temp)) {
                            nextLine.add(next);
                            if (k == 1 && next == n - 1) res ++;
                        }
                    }
                }
            }
            queue = nextLine;
            k --;
        }
        return res;
    }
}
