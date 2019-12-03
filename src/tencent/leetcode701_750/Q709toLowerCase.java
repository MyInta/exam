package tencent.leetcode701_750;

/**
 * @author inta
 * @date 2019/12/3
 * @describe 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
 * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: "Hello"
 * 输出: "hello"
 * 示例 2：
 *
 * 输入: "here"
 * 输出: "here"
 * 示例 3：
 *
 * 输入: "LOVELY"
 * 输出: "lovely"
 *
 */
public class Q709toLowerCase {
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }
    public String toLowerCase2(String str) {
        char[] str_chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : str_chars) {
            if (c <= 'Z' && c >= 'A') {
                sb.append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
