package leetcode_inta.leetcode1_50;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/9/2
 * @describe 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 */
public class Q20BracketIsValid {
    public boolean isValid(String s) {
        int nums = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < nums; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char temp = stack.pop();
                if (c - temp > 2 || c - temp < 0) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Q20BracketIsValid q = new Q20BracketIsValid();
        String s = "()";
        System.out.println(q.isValid(s));
    }
}
