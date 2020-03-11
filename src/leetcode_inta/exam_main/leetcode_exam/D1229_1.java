package leetcode_inta.exam_main.leetcode_exam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/29
 * @describe
 */
public class D1229_1 {
    public int[] sumZero(int n) {
        List<Integer> list = new ArrayList<>();
        if (n % 2 == 0) {
            int mid = n / 2;
            for (int i = 0; i < mid; i ++) {
                list.add(i + 1);
                list.add(-(i + 1));
            }
        } else {
            int mid = n / 2;
            for (int i = 0; i < mid; i ++) {
                list.add(i + 1);
                list.add(-(i + 1));
            }
            list.add(0);
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int i : list) {
            res[index ++] = i;
        }
        return res;
    }


}
