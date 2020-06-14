package leetcode_inta.exam_main.leetcode_exam2020.D193;

import java.util.*;

/**
 * @author inta
 * @date 2020/6/14
 * @describe
 */
public class Q2 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        PriorityQueue<Integer> p = new PriorityQueue<>();
        for (int a : map.keySet()) {
            p.add(map.get(a));
        }
        while (k > 0 && !p.isEmpty()) {
            k -= p.poll();
        }
        if (k == 0) return p.size();
        if (k < 0) return p.size() + 1;
        return 0;
    }
}
