package interview.I1_5;

/**
 * @author inta
 * @date 2020/2/23
 * @describe 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 */
public class I0102CheckPermutation {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] counts = new int[128];
        for (int i = 0; i < s1.length(); i ++) {
            counts[s1.charAt(i)] ++;
            counts[s2.charAt(i)] --;
        }
        for (int count : counts) {
            if (count != 0) return false;
        }
        return true;
    }
}
