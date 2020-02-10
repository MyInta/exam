package tencent.exam_main.leetcode_exam2020.D175;

import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/2/9
 * @describe
 */
public class D1 {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (i % 2 == 0) {
                if (set.contains(i / 2)) return true;
            }
            if (set.contains(i * 2)) return true;
            set.add(i);
        }
        return false;
    }
}
