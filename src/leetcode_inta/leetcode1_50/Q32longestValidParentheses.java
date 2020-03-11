package leetcode_inta.leetcode1_50;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/7
 * @describe 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */
public class Q32longestValidParentheses {
    public int longestValidParentheses(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < chars.length; i ++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    res = Math.max(res, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return res;
    }
}
