package tencent.leetcode551_600;

/**
 * @author inta
 * @date 2019/9/23
 * @describe 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例 1:
 *
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 */
public class Q557reverseWords {
    public String reverseWords(String s) {
        String[] all = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < all.length; i++) {
            char[] chars = all[i].toCharArray();
            for (int j = chars.length - 1; j >= 0; j--) {
                sb.append(chars[j]);
            }
            if (i != all.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
