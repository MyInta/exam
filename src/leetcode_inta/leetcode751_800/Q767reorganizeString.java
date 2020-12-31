package leetcode_inta.leetcode751_800;

/**
 * @author inta
 * @date 2020/11/30
 * @describe 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 *
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 *
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 *
 * S 只包含小写字母并且长度在[1, 500]区间内。
 */
public class Q767reorganizeString {

    public String reorganizeString(String S) {
        int size = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray()) {
            counts[c - 'a']++;
        }
        int max = Integer.MIN_VALUE, index = -1;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > (size + 1) / 2) return "";
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }
        char[] chars = new char[size];
        int cur = -2;
        while (counts[index] > 0) {
            cur += 2;
            chars[cur] = (char) ('a' + index);
            counts[index]--;
        }
        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                cur += 2;
                if (cur >= size) cur = 1;
                chars[cur] = (char) ('a' + i);
                counts[i]--;
            }
        }
        return String.valueOf(chars);
    }
}
