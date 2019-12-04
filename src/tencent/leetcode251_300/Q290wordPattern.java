package tencent.leetcode251_300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/4
 * @describe 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
 *
 */
public class Q290wordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> map = new HashMap<>();
        if (str.trim().equals("")) return false;
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) return false;
        for (int i = 0; i < strs.length; i ++) {
            if (!map.containsKey(strs[i]) && !map.containsValue(pattern.charAt(i))) {
                map.put(strs[i], pattern.charAt(i));
            } else if (!map.containsKey(strs[i]) || !map.get(strs[i]).equals(pattern.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
