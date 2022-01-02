package leetcode_inta.exam_main.leetcode_exam2021.D67;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q2 {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        if (2 * time + 1 > security.length) {
            return new ArrayList<>();
        }
        // 也就是让我们确认递增和递减区间
        List<Integer> res = new LinkedList<>();
        // 边界情况，time为0和1时，res为全部security
        if (time == 0) {
            for (int i = 0; i < security.length; i++) {
                res.add(i);
            }
            return res;
        }
        // time为1，特殊处理
        if (time == 1) {
            for (int i = 1; i < security.length - 1; i++) {
                if (security[i - 1] >= security[i] && security[i] <= security[i + 1]) {
                    res.add(i);
                }
            }
            return res;
        }
        // time在2以上时,判断遇到递增且长度为k时，之前k-当前位置都符合要求，并且移动到下一个非递增
        int left = 0;
        int right = 1;
        while (right < security.length) {
            if (security[right] > security[right - 1]) {
                // 开始往下继续找k-1个递增的
                int cur = right - 1;
                while (right < security.length) {
                    if (security[right] > security[right - 1]) {
                        right++;
                    }
                    if (right - cur > time) {
                        // 考虑添加
                        for (int i = left + time - 1; i <= cur; i++) {
                            res.add(i);
                        }
                        break;
                    }
                }
                left = right;
            } else {
               right++;
            }
        }
        return res;
    }
}
