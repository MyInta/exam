package tencent.leetcode201_250;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/12
 * @describe 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 */
public class Q242isAnagram {
    //之前做过一道字符匹配的题目，更复杂，用的就是hash表计数，此方法结果效率反而低了
    public boolean isAnagram(String s, String t) {
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        if (s_chars.length != t_chars.length) return false;
        Map<Character, Integer> s_map = new HashMap<>();
        Map<Character, Integer> t_map = new HashMap<>();
        int count = 0;
        for (char t_char : t_chars) {
            t_map.put(t_char, t_map.getOrDefault(t_char, 0) + 1);
        }
        for (char s_char : s_chars) {
            if (t_map.containsKey(s_char)) {
                s_map.put(s_char, s_map.getOrDefault(s_char, 0) + 1);
                //如果数量相等，就计数+1（相当于长度+1）
                if (s_map.get(s_char).equals(t_map.get(s_char))) {
                    count ++;
                }
            } else {
                return false;
            }
        }
        return count == t_map.size();
    }

    public boolean isAnagram2(String s, String t) {
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        if (s_chars.length != t_chars.length) return false;
        int[] diff = new int[26];
        for (char s_char : s_chars) {
            diff[s_char - 'a'] ++;
        }
        for (char t_char : t_chars) {
            diff[t_char - 'a'] --;
        }
        for (int i : diff) {
            if (i != 0) return false;
        }
        return true;
    }

}
