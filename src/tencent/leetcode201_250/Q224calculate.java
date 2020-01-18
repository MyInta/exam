package tencent.leetcode201_250;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/18
 * @describe 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Q224calculate {
    //用栈保存正值或者负值
    public int calculate(String s) {
        //sk保存数值
        Stack<Integer> sk = new Stack<>();
        //保存左括号前符号
        Stack<Integer> stack_sign = new Stack<>();
        //默认开始时是加号
        char sign = '+';
        int num = 0;
        int count = 0;
        char[] sChars = s.toCharArray();
        for (int i = 0; i < s.length(); i ++) {
            char c = sChars[i];
            if (isDigt(c)) {
                //如果是数字，就累加，考虑多位数字
                num = num * 10 + (c - '0');
            } else if (c != ' ') {
//                System.out.println(count);
                //非数字就开始往stack里面添加
                switch (c) {
                    case '+' :
                        count += sign == '+' ? num : -num;
                        //变更前缀
                        sign = c;
                        break;
                    case '-' :
                        count += sign == '+' ? num : -num;
                        //变更前缀
                        sign = c;
                        break;
                    case '(' :
                        count += sign == '+' ? num : -num;
                        sk.push(count);
                        stack_sign.push(sign == '+' ? 1 : -1);
                        //注意括号后默认开始为+号
                        sign = '+';
                        count = 0;
                        break;
                    case ')' :
                        count += sign == '+' ? num : -num;
                        count = count * stack_sign.pop() + sk.pop();
                        //注意括号后默认开始为+号
                        sign = '+';
                        break;
                }
                //重置数值
                num = 0;
            }
        }
        if (sign == '+') {
            count += num;
        } else {
            count -= num;
        }
        return count;
    }
    private boolean isDigt(char c) {
        return c >= '0' && c <= '9';
    }
}
