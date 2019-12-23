package tencent.exam_main.leetcode_exam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2019/12/22
 * @describe
 */
public class D1222_3 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int res = 0;
        int len = s.length();
        int size = minSize;
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        while (index + size <= len) {
            String str = s.substring(index, index + size);
            map.put(str, map.getOrDefault(str, 0) + 1);
            index ++;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (validMaxLetters(maxLetters, entry.getKey())) {
                res = Math.max(res, entry.getValue());
            }
        }
        return res;
    }
    private boolean validMaxLetters(int maxLetters, String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i ++) {
            set.add(str.charAt(i));
        }
        return set.size() <= maxLetters;
    }
}
