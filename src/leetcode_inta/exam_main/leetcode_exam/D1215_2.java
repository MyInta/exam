package leetcode_inta.exam_main.leetcode_exam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/15
 * @describe
 */
public class D1215_2 {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        solution(low, high, res);
        return res;
    }
    private void solution(int low, int high, List<Integer> res) {
        int n = 0;
        int temp_low = low;
        while (temp_low > 0) {
            n ++;
            temp_low = temp_low / 10;
        }

        int m = 0;
        int temp_high = high;
        while (temp_high > 0) {
            m ++;
            temp_high /= 10;
        }
        String str = "123456789";
        for (int i = n; i <= m; i ++) {
            for (int j = 0; j <= str.length() - i; j ++) {
                int num = Integer.valueOf(str.substring(j, j + i));
                if (num <= high && num >= low) {
                    res.add(num);
                }
            }
        }
    }
}
