package leetcode_inta.exam_main.leetcode_exam2020.D194;

import java.util.*;

/**
 * @author inta
 * @date 2020/6/21
 * @describe
 */
public class Q3 {

    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[rains.length];
        Stack<Integer> indexes = new Stack<>();
        int consume = 0;
        //记录填坑数量
        int pre = 0;
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain == 0) {
                if (pre > 0) consume ++;
                indexes.add(i);
                continue;
            }
            ans[i] = -1;
            if (!map.containsKey(rain) || map.get(rain) == 0) {
                map.put(rain, 1);
                pre ++;
            } else {
                if (map.get(rain) == 1 && consume == 0) return new int[0];
                ans[indexes.pop()] = rain;
                map.put(rain, 0);
                consume --;
                pre --;
            }
        }
        while (!indexes.isEmpty()) {
            ans[indexes.pop()] = 1;
        }
        return ans;
    }
}
