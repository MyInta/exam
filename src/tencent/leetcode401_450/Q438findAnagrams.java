package tencent.leetcode401_450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/10
 * @describe 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 */
public class Q438findAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        int count = 0;
        char[] s_chars = s.toCharArray();
        char[] p_chars = p.toCharArray();
        Map<Character, Integer> p_map = new HashMap<>();
        Map<Character, Integer> cur_map = new HashMap<>();
        for (char p_c : p_chars) {
            p_map.put(p_c, p_map.getOrDefault(p_c, 0) + 1);
        }
        while (right < s_chars.length) {
            if (p_map.containsKey(s_chars[right])) {
                cur_map.put(s_chars[right], cur_map.getOrDefault(s_chars[right], 0) + 1);
                if (cur_map.get(s_chars[right]).equals(p_map.get(s_chars[right]))) {
                    count ++;
                }
            }
            right ++;
            while (count == p_map.size()) {
                if (right - left == p_chars.length) {
                    //说明找到合适的了
                    res.add(left);
                }
                if (p_map.containsKey(s_chars[left])) {
                    if (cur_map.get(s_chars[left]).equals(p_map.get(s_chars[left]))) {
                        count --;
                    }
                    cur_map.put(s_chars[left], cur_map.getOrDefault(s_chars[left], 0) - 1);
                }
                left ++;
            }
        }
        return res;
    }
}
