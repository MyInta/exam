package leetcode_inta.exam_main.leetcode_exam2021.D244;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2021/6/6
 * @describe
 */
public class Q2 {
    // 优先队列保存大小关系，map保存数量关系
    public int reductionOperations(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                pq.add(num);
                map.put(num, 1);
            }
        }
        int res = 0;
        while (pq.size() > 1) {
            int target = pq.poll();
            map.put(pq.peek(), map.get(pq.peek()) + map.get(target));
            res += map.get(target);
        }
        return res;
    }
}
