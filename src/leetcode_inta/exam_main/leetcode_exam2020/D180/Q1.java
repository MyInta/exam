package leetcode_inta.exam_main.leetcode_exam2020.D180;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2020/3/15
 * @describe
 */
public class Q1 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i ++) {
            int temp = matrix[i][0];
            for (int j = 1; j < m; j ++) {
                temp = Math.min(temp, matrix[i][j]);
            }
            set.add(temp);
        }
        for (int j = 0; j < m; j ++) {
            int temp = matrix[0][j];
            for (int i = 1; i < n; i ++) {
                temp = Math.max(temp, matrix[i][j]);
            }
            if (set.contains(temp)) res.add(temp);
        }
        return res;
    }

}
