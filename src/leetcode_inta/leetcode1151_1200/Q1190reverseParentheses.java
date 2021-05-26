package leetcode_inta.leetcode1151_1200;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/11
 * @describe 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 */
public class Q1190reverseParentheses {
    // 笨办法先来一下
    public String reverseParentheses(String s) {
        if (s == null || s.length() == 0) return s;
        char[] s_chars = s.toCharArray();
        // 用于保存左括号地索引位置
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s_chars.length; i++) {
            if (s_chars[i] == '(') {
                stack.push(i);
            }
            if (s_chars[i] == ')') {
                reverse(s_chars, stack.pop() + 1, i - 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s_chars) {
            if (c != '(' && c != ')') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 用于交换从start-end内数组的元素
    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    // 大神都是言简意赅地使用递归解决
    private int index = -1;
    public String reverseParentheses2(String s) {
        StringBuilder sb = new StringBuilder();
        while (++index < s.length()) {
            char c = s.charAt(index);
            if (c == '(') {
                //遇到左括号，那么内部字符串就需要反转,使用StringBuilder提供的反转方法
                sb.append(new StringBuilder(reverseParentheses2(s)).reverse());
            }
            if (c == ')') {
                //遇到右括号，就把括号前的添加的字符串sb返回
                break;
            }
            //如果都不是，就正常添加
            sb.append(c);
        }
        return sb.toString();
    }
}
