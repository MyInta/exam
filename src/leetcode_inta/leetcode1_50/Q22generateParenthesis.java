package leetcode_inta.leetcode1_50;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/8
 * @describe 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class Q22generateParenthesis {
    //思路：左括号和右括号计数，先后顺序必定为先有左再有右，左满，右补满，每次左都新建字符串
    private List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        add(new StringBuilder(), 0, 0, n);
        return res;
    }
    private void add(StringBuilder sb, int countLeft, int countRight, int n) {
        if (countLeft > countRight && countLeft < n) {
            add(new StringBuilder(sb).append('('), countLeft + 1, countRight, n);
            add(sb.append(')'), countLeft, countRight + 1, n);
        } else if (countLeft == countRight && countLeft < n) {
            add(new StringBuilder(sb).append('('), countLeft + 1, countRight, n);
        } else if (countLeft == n) {
            for (int i = 0; i < n - countRight; i ++) {
                sb.append(')');
            }
            res.add(sb.toString());
        }
    }

    //递归精简思路
    private class Q22generateParenthesis2{
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            add(res, new StringBuilder(), 0, 0, n);
            return res;
        }
        private void add(List<String> res, StringBuilder sb, int countleft, int countRight, int n) {
            if (countRight == n) {
                res.add(sb.toString());
                return;
            }
            if (countleft < n) {
                add(res, new StringBuilder(sb).append('('), countleft + 1, countRight, n);
            }
            if (countRight < countleft) {
                add(res, sb.append(')'), countleft, countRight + 1, n);
            }
        }
    }
    //状态保持，黑盒子思想,效率会低，但最好理解
    private class Q22generateParenthesis3{
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) res.add("");
            for (int i = 0; i < n; i ++) {
                for (String str_inert : generateParenthesis(i)) {
                    for (String str_out : generateParenthesis(n - i - 1)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("(").append(str_inert).append(")").append(str_out);
                        res.add(sb.toString());
                    }
                }
            }
            return res;
        }
    }
}
