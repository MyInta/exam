package leetcode_inta.leetcode351_400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/11/4
 * @describe 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例:
 *
 * 输入：
 * s = "abcd"
 * t = "abcde"
 *
 * 输出：
 * e
 *
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 */
public class Q389findTheDifference {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> s_map = new HashMap<>();
        for (char c : s.toCharArray()) {
            s_map.put(c, s_map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!s_map.containsKey(c) || s_map.get(c) == 0) return c;
            s_map.put(c, s_map.get(c) - 1);
        }
        return ' ';
    }

    //既然题目说是小写字母，那就不用map试试
    public char findTheDifference2(String s, String t) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a'] ++;
        }
        for (char c : t.toCharArray()) {
            if (chars[c - 'a'] == 0) return c;
            chars[c - 'a'] --;
        }
        return ' ';
    }
    //还有累加ascll，差值为解
    //还有异或算法，感觉异或应该是效率最高的，就实现一下
    public char findTheDifference3(String s, String t) {
        int res = 0;
        for (char s_c : s.toCharArray()) {
            res ^= s_c;
        }
        for (char t_c : t.toCharArray()) {
            res ^= t_c;
        }
        return (char)res;
    }
}
