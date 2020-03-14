package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/11/19
 * @describe 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。
 * 给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 *
 * 输入: "abab"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: "aba"
 *
 * 输出: False
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 *
 * 输出: True
 *
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class Q459repeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        String new_s = (s + s).substring(1, s.length() * 2 - 1);
        return new_s.contains(s);
    }
    //使用字符串挨个比较的方法
    public boolean repeatedSubstringPattern2(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }
        int len = s.length();
        for (int i = 1; i <= (len >> 1); i ++) {
            if (len % i == 0) {
                int j = i;
                String str = s.substring(0, i);
                while (j < len && s.substring(j, j + i).equals(str)) {
                    j += i;
                }
                if (j == len) return true;
            }
        }
        return false;
    }
}
