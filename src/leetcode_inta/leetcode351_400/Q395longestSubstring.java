package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2019/10/24
 * @describe 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 *
 * 示例 1:
 *
 * 输入:
 * s = "aaabb", k = 3
 *
 * 输出:
 * 3
 *
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2:
 *
 * 输入:
 * s = "ababbc", k = 2
 *
 * 输出:
 * 5
 *
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *
 */
public class Q395longestSubstring {
    //从整体开始，计数，逐渐分割，对分割块重复操作，指针逼近，返回最终符合要求最长字符串长度
    public int longestSubstring(String s, int k) {
        if (s == null || k > s.length()) return 0;
        if (k < 2) return s.length();
        return solution(s.toCharArray(), k, 0, s.length() - 1);
    }
    private int solution(char[] s_chars, int k, int left, int right) {
        if (right - left + 1 < k) return 0;
        int[] counts = new int[26];
        for (int i = left; i <= right; i ++) {
            counts[s_chars[i] - 'a'] ++;
        }
        //然后开始左右指针的逼近
        while (right - left + 1 >= k && counts[s_chars[left] - 'a'] < k) {
            left ++;
        }
        while (right - left + 1 >= k && counts[s_chars[right] - 'a'] < k) {
            right --;
        }
        //若逼近后的字符串长度小于k，弃之
        //分割字符串递归
        for (int i = left; i <= right; i ++) {
            if (counts[s_chars[i] - 'a'] < k) {
                return Math.max(solution(s_chars, k, left, i - 1), solution(s_chars, k, i + 1, right));
            }
        }
        return right - left + 1;
    }
}
