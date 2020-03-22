package leetcode_inta.exam_main.leetcode_exam2020.D181;

import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/22
 * @describe
 */
public class Q1 {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        int i = 0;
        for (int num : nums) {
            list.add(index[i ++], num);
        }
        int[] res = new int[list.size()];
        i = 0;
        for (int j : list) {
            res[i ++] = j;
        }
        return res;
    }
}
