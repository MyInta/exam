package leetcode_inta.leetcode201_250;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/29
 * @describe 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class Q227calculate {
    public int calculate(String s) {
        String clean_s = s.replace(" ","");
        // 队列，方便后续从头开始遍历
        LinkedList<String> queue = new LinkedList<>();
        for (int i = 0; i < clean_s.length(); i++) {
            char temp = clean_s.charAt(i);
            if (temp == '+' || temp == '-') {
                // 说明是运算符
                queue.add(String.valueOf(temp));
                System.out.println(String.valueOf(temp));
            } else if (temp == '*' || temp == '/') {
                // 乘除优先计算
                String last = queue.pollLast();
                int isNum = 0;
                i ++;
                System.out.println(clean_s.charAt(i) + "样板");
                while (clean_s.charAt(i) >= '0' && clean_s.charAt(i) <= '9') {
                    isNum = isNum * 10 + (clean_s.charAt(i) - '0');
                    System.out.println(String.valueOf(isNum) + "测试");
                    i ++;
                    if (i == clean_s.length()) {
                        break;
                    }
                }
                i --;
                int sum = temp == '*' ? Integer.valueOf(last) * isNum
                        :  Integer.valueOf(last) / isNum;
                queue.add(String.valueOf(sum));
                System.out.println(String.valueOf(sum));
            } else {
                int isNum = 0;
                while (clean_s.charAt(i) >= '0' && clean_s.charAt(i) <= '9') {
                    isNum = isNum * 10 + (clean_s.charAt(i) - '0');
                    i ++;
                    if (i == clean_s.length()) {
                        break;
                    }
                }
                i --;
                queue.add(String.valueOf(isNum));
                System.out.println(String.valueOf(isNum));
            }
        }
        int res = 0;
        String q = queue.pop();
        res += Integer.valueOf(q);
        while (!queue.isEmpty()) {
            String method = queue.pop();
            int next = Integer.valueOf(queue.pop());
            if (method.equals("+")) {
                res += next;
            } else if (method.equals("-")){
                res -= next;
            }
        }
        return res;
    }

    // 网上解答比自己写的思路清晰，且效率高，核心在于用stack保存值，而遍历每个运算符号
    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sigh = '+';
        for (int i =0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp >= '0') {
                num = num * 10 + (temp - '0');
            }
            if (temp < '0' && temp != ' ' || i == s.length() - 1) {
                switch (sigh) {
                    case '+' : stack.add(num);break;
                    case '-' : stack.add(-num);break;
                    case '*' : stack.add(stack.pop() * num);break;
                    case '/' : stack.add(stack.pop() / num);break;
                }
                sigh = temp;
                num = 0;
            }
        }
        int res = 0;
        for (int i : stack) {
            res += i;
        }
        return res;
    }

    public int calculate3(String s) {
        Stack<Integer> res = new Stack<>();
        int curNum = 0;
        char calMark = '+';
        for (char c : s.toCharArray()) {
            if (c >= '0') {
                curNum = curNum * 10 + (c - '0');
            }

            if (c < '0' && c != ' ') {
                switch(calMark) {
                    case '+':
                        res.push(curNum);
                        break;
                    case '-':
                        res.push(-curNum);
                        break;
                    case '*':
                        res.push(res.pop() * curNum);
                        break;
                    case '/':
                        res.push(res.pop() / curNum);
                        break;
                }
                calMark = c;
                curNum = 0;
            }

        }
        switch(calMark) {
            case '+':
                res.push(curNum);
                break;
            case '-':
                res.push(-curNum);
                break;
            case '*':
                res.push(res.pop() * curNum);
                break;
            case '/':
                res.push(res.pop() / curNum);
                break;
        }
        int reslut = 0;
        for (int num : res) {
            reslut += num;
        }
        return reslut;
    }
}
