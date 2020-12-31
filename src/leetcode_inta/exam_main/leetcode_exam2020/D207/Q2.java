package leetcode_inta.exam_main.leetcode_exam2020.D207;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2020/9/20
 * @describe
 */
public class Q2 {

    public int maxUniqueSplit(String s) {
        Map<Integer, Set<String>> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            map.put(i, new HashSet<>());
        }
        boolean flag;
        int[] dp = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            int pre = dp[i];
            flag = false;
            for (int j = 0; j < i; j++) {
                if (!map.get(i - j).contains(s.substring(i - j, i + 1))) {
                    flag = true;
                    pre = Math.max(dp[i], dp[i - j]);
                    map.get(i - j).add(s.substring(i - j, i + 1));
                }
            }
            if (flag) dp[i] = pre + 1;
            dp[i] = pre + 1;
        }
        return dp[len];
    }
}
