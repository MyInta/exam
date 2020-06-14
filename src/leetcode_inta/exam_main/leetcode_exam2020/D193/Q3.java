package leetcode_inta.exam_main.leetcode_exam2020.D193;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author inta
 * @date 2020/6/14
 * @describe
 */
public class Q3 {
    //保存所有天数，从最后一天开始往前遍历，看能否成功，第一个不能成功的就是需要超过的天数
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) return -1;
        Set<Integer> set = new HashSet<>();
        for (int b : bloomDay) set.add(b);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int s : set) pq.add(s);
        int res = -1;
        while (!pq.isEmpty()) {
            int target = pq.poll();
            int count = 0;
            for (int i = 0; i <= bloomDay.length - k; i ++) {
                int temp = 0;
                while (temp < k) {
                    if (bloomDay[i + temp] > target) {
                        i += temp - 1;
                        break;
                    }
                    temp ++;
                }
                if (temp == k) {
                    count ++;
                    i += temp - 1;
                }
                if (count == m) break;
            }
            if (count < m) break;
            res = target;
        }
        return res;
    }
}
