package leetcode_inta.leetcode1351_1400;

/**
 * @author inta
 * @date 2020/6/5
 * @describe 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 *
 *     从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 *     从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 *     重复步骤 2 ，直到你没法从 s 中选择字符。
 *     从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 *     从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 *     重复步骤 5 ，直到你没法从 s 中选择字符。
 *     重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 *
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 *
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 *
 * 示例 2：
 *
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 *
 * 示例 3：
 *
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 *
 * 示例 4：
 *
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 *
 * 示例 5：
 *
 * 输入：s = "spo"
 * 输出："ops"
 *
 * 提示：
 *     1 <= s.length <= 500
 *     s 只包含小写英文字母。
 *
 */
public class Q1370sortString {
    //暴力思想，反正都是小写字母，就统计a-z对应数量，顺着倒着添加，知道不存在字母
    public String sortString(String s) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean flag = false;
            for (int i = 0; i < 26; i++) {
                if (chars[i] > 0) {
                    sb.append((char)('a' + i));
                    chars[i] --;
                    flag = true;
                }
            }
            if (flag) {
                for (int i = 25; i >= 0; i--) {
                    if (chars[i] > 0) {
                        sb.append((char)('a' + i));
                        chars[i] --;
                    }
                }
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
