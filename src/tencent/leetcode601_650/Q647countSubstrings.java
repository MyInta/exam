package tencent.leetcode601_650;

/**
 * @author inta
 * @date 2019/10/11
 * @describe 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 *
 */
public class Q647countSubstrings {
    public int countSubstrings(String s) {
        int res = 0;
        char[] s_chars = s.toCharArray();
        for (int i = 0; i < s_chars.length; i ++) {
            if (i == s_chars.length - 1) {
                return res += 1;
            }
            res += palindrome(s_chars, i, i);
            res += palindrome(s_chars, i, i + 1);
        }
        return res;
    }
    private int palindrome(char[] s_chars, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s_chars.length && s_chars[left] == s_chars[right]) {
            left --;
            right ++;
            count ++;
        }
        return count;
    }
}
