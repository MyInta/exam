package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2020/1/22
 * @describe 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class Q680validPalindrome {
    public boolean validPalindrome(String s) {
        //因为有一个需要删除，需要从原有方法中抽离出来,无外乎二选一，删的是右侧或左侧
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isValid(left, right - 1, s) || isValid(left + 1, right, s);
            }
            left ++;
            right --;
        }
        return true;
    }
    private boolean isValid(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start ++) != s.charAt(end --)) return false;
        }
        return true;
    }
}
