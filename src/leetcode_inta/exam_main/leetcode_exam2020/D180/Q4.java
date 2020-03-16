package leetcode_inta.exam_main.leetcode_exam2020.D180;

import java.util.*;

/**
 * @author inta
 * @date 2020/3/15
 * @describe
 */
public class Q4 {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i ++) {
            sorted[i] = i;
        }
        Arrays.sort(sorted, (a, b) -> efficiency[b] - efficiency[a]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            sum += speed[sorted[i]];
            pq.add(speed[sorted[i]]);
            if (pq.size() > k) sum -= pq.poll();
            res = Math.max(res, sum * efficiency[sorted[i]]) % 1_000_000_007;
        }
        return (int)res;
    }
}
