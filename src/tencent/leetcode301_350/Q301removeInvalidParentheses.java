package tencent.leetcode301_350;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/4
 * @describe 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 *
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 *
 * 输入: ")("
 * 输出: [""]
 *
 */
public class Q301removeInvalidParentheses {
    private List<String> res = new ArrayList<>();
    public List<String> removeInvalidParentheses(String s) {
        //统计左右括号异常的数量
        int lCount = 0;
        int rCount = 0;
        char[] sChars = s.toCharArray();
        for (char c : sChars) {
            if (c == '(') {
                lCount ++;
            } else if (c == ')') {
                if (lCount > 0) {
                    lCount --;
                } else {
                    rCount ++;
                }
            }
        }
        dfs(sChars, 0, lCount, rCount);
        return res;
    }
    //深度遍历，并删除无效括号
    private void dfs(char[] sChars, int start, int left, int right) {
        //每次修改过后，新的数组都需要判断是否合理，万一删错了呢
        if (left == 0 && right == 0 && isValid(sChars)) {
            res.add(String.valueOf(sChars));
            return;
        }
        for (int i = start; i < sChars.length; i ++) {
            if (i - 1 >= start && sChars[i] == sChars[i - 1]) {
                continue;
            }
            char[] newChar = new char[sChars.length - 1];
            int index = 0;
            for (int k = 0; k < sChars.length; k ++) {
                if (k != i) {
                    newChar[index ++] = sChars[k];
                }
            }
            if (left > 0 && sChars[i] == '(') {
                dfs(newChar, i, left - 1, right);
            }
            if (right > 0 && sChars[i] == ')') {
                dfs(newChar, i, left, right - 1);
            }
        }

    }
    //判断整体是否有效
    private boolean isValid(char[] sChars) {
        int l = 0;
        for (char c : sChars) {
            if (c == '(') {
                l ++;
            } else if (c == ')') {
                l --;
                if (l < 0) {
                    return false;
                }
            }
        }
        return l == 0;
    }

}
