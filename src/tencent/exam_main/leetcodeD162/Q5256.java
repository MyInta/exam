package tencent.exam_main.leetcodeD162;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/10
 * @describe
 */
public class Q5256 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int c :colsum) {
            if (c == 2) {
                list1.add(1);
                list2.add(1);
                upper --;
                lower --;
            }
            if (c == 0) {
                list1.add(0);
                list2.add(0);
            }
            if (upper < 0 || lower < 0) {
                return res;
            }
            if (c == 1) {
                list1.add(3);
                list2.add(3);
            }
        }
        int sum = 0;
        for (int i : list1) {
            if (i == 3) sum ++;
        }
        if (sum != upper + lower) return res;

        for (int i = 0; i < list1.size(); i ++) {
            if (list1.get(i) == 3) {
                if (upper > 0) {
                    list1.set(i, 1);
                    list2.set(i, 0);
                    upper --;
                } else {
                    list1.set(i, 0);
                    list2.set(i, 1);
                }
            }
        }
        res.add(list1);
        res.add(list2);
        return res;
    }
}
