package tencent.leetcode1201_1250;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/11
 * @describe 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *  
 *
 * 示例 1：
 *
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * 示例 2：
 *
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * 示例 3：
 *
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 * 示例 4：
 *
 * 输入：s = "(a(b(c)d)"
 * 输出："a(b(c)d)"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s[i] 可能是 '('、')' 或英文小写字母
 *
 */
public class Q1249minRemoveToMakeValid {
    //两个栈搞定吧,可行，但效率低
    public String minRemoveToMakeValid(String s) {
        //用来保存括号的索引位置
        Stack<Integer> stack_l = new Stack<>();
        //保存需要删除的索引位置
        Set<Integer> set_del = new HashSet<>();
        char[] s_chars = s.toCharArray();
        for (int i = 0; i < s_chars.length; i ++) {
            if (s_chars[i] == '(') {
                stack_l.push(i);
            } else if (s_chars[i] == ')') {
                if (stack_l.isEmpty()) {
                    //如果没有左括号，说明该右括号必须删
                    set_del.add(i);
                } else {
                    stack_l.pop();
                }
            }
        }
        while (!stack_l.isEmpty()) {
            set_del.add(stack_l.pop());
        }
        //遍历一遍之后，已经找到所有需要删除的括号位置
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s_chars.length; i ++) {
            if (!set_del.contains(i)) sb.append(s_chars[i]);
        }
        return sb.toString();
    }
}
