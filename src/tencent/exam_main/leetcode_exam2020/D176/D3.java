package tencent.exam_main.leetcode_exam2020.D176;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/2/16
 * @describe
 */
public class D3 {
    public int maxEvents(int[][] events) {
        PriorityQueue<int[]> p = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] - o2[0] == 0) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        for (int[] e : events) {
            p.add(e);
        }
        int res = 0;
        int cur = Integer.MIN_VALUE;
        while (!p.isEmpty()) {
            int[] temp = p.poll();
            if (temp[0] > cur) {
                cur = temp[0] + 1;
                res ++;
            } else if (temp[0] < cur) {
                temp[0] = cur;
                if (temp[1] >= temp[0]) {
                    p.add(temp);
                }
            } else {
                cur ++;
                res ++;
            }
        }
        return res;
    }
}
