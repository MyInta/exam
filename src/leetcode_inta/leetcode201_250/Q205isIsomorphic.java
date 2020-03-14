package leetcode_inta.leetcode201_250;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/10
 * @describe 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 */
public class Q205isIsomorphic {
    //第一反应，最蠢的方法，map保存字符映射关系
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            //必须得是s为键，t为值，而且s的不同键不能指向同一个值
            if (!map.containsKey(s.charAt(i)) && !map.containsValue(t.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            } else if (map.containsKey(s.charAt(i))) {
                //如果key取得v与t对应位置不相等，为false
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) return false;
            } else {
                //剩下一种可能即map不含该key但是有t为v得情况都是false
                return false;
            }
        }
        return true;
    }

    //看大神使用数组操作
    public boolean isIsomorphic2(String s, String t) {
        char[] s_c = new char[255];
        char[] t_c = new char[255];
        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();
        for (int i = 0; i < s_chars.length; i ++) {
            if (s_c[s_chars[i]] != 0 && s_c[s_chars[i]] != t_chars[i] || t_c[t_chars[i]] != 0 && t_c[t_chars[i]] != s_chars[i]) {
                return false;
            }
            s_c[s_chars[i]] = t_chars[i];
            t_c[t_chars[i]] = s_chars[i];
        }
        return true;
    }
}
