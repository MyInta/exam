package tencent.exam_main.leetcode_exam2020;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/19
 * @describe
 */
public class D0119_4 {
    //思路，map保存点和最大，依次遍历，贪心
    public int minTaps(int n, int[] ranges) {
        boolean valid = true;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= n; i ++) {
            int left = i - ranges[i];
            int right = i + ranges[i];
            if (left < 0) left = 0;
            if (right > n) right = n;
            if (left == 0 && right == n) return 1;
            map.put(left, Math.max(right, map.getOrDefault(left, -1)));
        }
        int start = 0;
        int end = map.getOrDefault(0, -1);
        if (end < 0) return -1;
        while (end < n) {
            int max = -1;
            for (int i = start + 1; i <= end; i ++) {
                int v = map.getOrDefault(i, -1);
                if (v > max) max = v;
            }
            if (max <= end) {
                valid = false;
                break;
            }
            start = end;
            end = max;
            res ++;
        }
        if (!valid) return -1;
        return res == 0 ? -1 : res + 1;
    }
}
