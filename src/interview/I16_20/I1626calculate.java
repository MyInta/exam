package interview.I16_20;

import java.util.*;

/**
 * @author inta
 * @date 2020/4/2
 * @describe 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 *
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 *
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 *
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 说明：
 *
 *     你可以假设所给定的表达式都是有效的。
 *     请不要使用内置的库函数 eval。
 *
 */
public class I1626calculate {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char pre = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0') {
                num = num * 10 + (c - '0');
            }
            //如果是运算符，并且不为空格,或者到最后一个位置，都要考虑添加操作
            if (c < '0' && c != ' ' || i == s.length() - 1) {
                switch (pre) {
                    case '+' : stack.push(num);break;
                    case '-' : stack.push(-num);break;
                    case '*' : stack.push(stack.pop() * num);break;
                    case '/' : stack.push(stack.pop() / num);break;
                }
                //更新运算符（就算是空格也没事）
                pre = c;
                //重置计数num
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
