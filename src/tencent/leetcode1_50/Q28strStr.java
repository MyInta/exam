package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/27
 * @describe 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */
public class Q28strStr {
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) return 0;
        if (needle.length() > haystack.length()) return -1;
        char[] h_chars = haystack.toCharArray();
        char[] n_chars = needle.toCharArray();
        for (int i = 0; i < h_chars.length - n_chars.length + 1; i ++) {
            int j;
            for (j = 0; j <n_chars.length; j ++) {
                if (h_chars[i + j] != n_chars[j]) {
                    break;
                }
            }
            if (j == n_chars.length) return i;
        }
        return -1;
    }
}
