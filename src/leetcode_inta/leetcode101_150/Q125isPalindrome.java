package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/10/26
 * @describe 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 */
public class Q125isPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.equals("")) return true;
        char[] chars = s.trim().toCharArray();
        int len = chars.length;
        int right = len - 1;
        for (int i = 0; i < right; i ++, right --) {
            while (i < right && !isValid(chars[i])) {
                i ++;
            }
            while ( right > i && !isValid(chars[right])) {
                right --;
            }
            if (i == right) return true;
            if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[right])) return false;
        }
        return true;
    }
    private boolean isValid(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9';
    }
}
