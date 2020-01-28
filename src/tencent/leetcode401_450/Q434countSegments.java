package tencent.leetcode401_450;

/**
 * @author inta
 * @date 2020/1/28
 * @describe 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 *
 * 输入: "Hello, my name is John"
 * 输出: 5
 *
 */
public class Q434countSegments {
    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        boolean findWord = false;
        for (char c : s.toCharArray()) {
            if (findWord && c == ' ') {
                res ++;
                findWord = false;
            } else if (c != ' ') {
                findWord = true;
            }
        }
        return findWord ? res + 1 : res;
    }
}
