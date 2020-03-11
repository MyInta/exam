package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/8
 * @describe 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 *
 */
public class Q58lengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        String[] temp = s.split(" ");
        if (temp.length == 0) return 0;
        return temp[temp.length - 1].length();
    }

    public int lengthOfLastWord2(String s) {
        s = s.trim();
        //不用担心索引找不到的情况，因为start为-1时候，相当于指针放在字符串前一位，不影响return结果
        int start = s.lastIndexOf(" ");
        return s.length() - start - 1;
    }
}
