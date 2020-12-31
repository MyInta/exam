package something.lexin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/9/9
 * @describe
 */
public class Q1 {

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int[] pre : preferences) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < pre.length; i++) {
                map.put(pre[i], pre.length - i);
            }
            list.add(map);
        }
        int res = 0;
        Map<Integer, Integer> p_map = new HashMap<>();
        for (int[] pair : pairs) {
            int first = pair[0];
            int second = pair[1];
            p_map.put(first, list.get(first).get(second));
            p_map.put(second, list.get(second).get(first));
        }
        for (int i = 0; i < n; i++) {
            int v = p_map.get(i);
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                int high = list.get(i).get(j);
                if (high > v) {
                    if (list.get(j).get(i) > p_map.get(j)) res++;
                }
            }
        }

        return res;
    }

}
