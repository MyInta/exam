package leetcode_inta.exam_main.leetcode_exam2020.D177;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2020/2/23
 * @describe
 */
public class Q3 {
    public int[] closestDivisors(int num) {
        PriorityQueue<int[]> p = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o1[0] - o2[1] + o2[0];
            }
        });
        int num1 = num + 1;
        int num2 = num + 2;
        for (int i = 1; i < Math.sqrt(num + 2); i ++) {
            if (num1 % i == 0) p.add(new int[]{i, num1/i});
            if (num2 % i == 0) p.add(new int[]{i, num2/i});
        }
        return p.poll();
    }
}
