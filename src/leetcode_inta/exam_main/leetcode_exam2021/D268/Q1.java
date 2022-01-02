package leetcode_inta.exam_main.leetcode_exam2021.D268;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2021/11/21
 * @describe
 */
public class Q1 {
    // 数据量不大，直接暴力
    public int maxDistance(int[] colors) {
        int res = 0;
        for (int i = 0; i < colors.length; i++) {
            for (int j = colors.length - 1; j > i; j--) {
                if (colors[j] != colors[i]) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }
}
