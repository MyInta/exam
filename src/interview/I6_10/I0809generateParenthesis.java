package interview.I6_10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/13
 * @describe 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 *
 * 说明：解集不能包含重复的子集。
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
public class I0809generateParenthesis {
    //看了大神的解答才有思路，自己想这个回溯总是有地方没转过弯来
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        dfs(n, n, new StringBuilder());
        return res;
    }
    private List<String> res;
    private void dfs(int a, int b, StringBuilder sb) {
        if (a == 0 && b == 0) {
            res.add(sb.toString());
            return;
        }
        if (a > 0) {
            sb.append('(');
            dfs(a - 1, b, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (a != b) {
            sb.append(')');
            dfs(a, b - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
