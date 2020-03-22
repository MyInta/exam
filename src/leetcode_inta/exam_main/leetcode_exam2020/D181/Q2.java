package leetcode_inta.exam_main.leetcode_exam2020.D181;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/22
 * @describe
 */
public class Q2 {
    public int sumFourDivisors(int[] nums) {
        int res = 0;
        counts = new int[100000];
        for (int num : nums) {
            res += getSum(num);
        }
        return res;
    }
    private int[] counts;
    private int getSum(int num) {
        if (counts[num] == -1) return 0;
        if (counts[num] > 0) return counts[num];
        if (num == 1) {
            counts[num] = -1;
            return 0;
        }
        int res = 0;
        List<Integer> list = new ArrayList<>();
        int target = 1;
        while (num > 1 && num >= target) {
            if (num % target == 0) {
                list.add(target);
            }
            if (list.size() > 4) {
                counts[num] = -1;
                return 0;
            }
            target ++;
        }
        if (list.size() != 4) {
            counts[num] = -1;
            return 0;
        }
        int sum = 0;
        for (int i : list) {
//            System.out.println(i);
            sum += i;
        }
        counts[num] = sum;
        res += sum;
        return res;
    }
}
