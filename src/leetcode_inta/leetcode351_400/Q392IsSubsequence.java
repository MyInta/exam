package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2019/9/19
 * @describe 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 *
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 *
 * 返回 false.
 *
 */
public class Q392IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        boolean flag = true;
        int cur = 0;
        for (int i = 0; i < s_len&&flag; i++) {
            flag = false;
            for (int j = cur; j < t_len; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    flag = true;
                    cur = j + 1;
                    break;
                }
            }
        }
        return flag;
    }

    //效率没有方法1高 TODO 为什么少一层for循环效率反而低了？
    private class Q392IsSubsequence2 {
        public boolean isSubsequence(String s, String t) {
            int s_index = 0;
            int len = s.length();
            for (int i = 0; i < t.length()&&s_index < len; i++) {
                if (s.charAt(s_index) == t.charAt(i)) {
                    s_index++;
                }
            }
            return s_index == len ? true : false;
        }
    }

    //此方法效率极高
    private class Q392IsSubsequence3 {
        public boolean isSubsequence(String s, String t) {
            int index = -1;
//            char[] chars = s.toCharArray();
//            for (char c: s.chars) {
            for (char c: s.toCharArray()) {
                index = t.indexOf(c, index + 1);
                if (index == -1) {
                    return false;
                }
            }
            return true;
        }
    }
}
