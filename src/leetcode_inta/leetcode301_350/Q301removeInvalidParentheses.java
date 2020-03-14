package leetcode_inta.leetcode301_350;

import java.util.ArrayList;
import java.util.List;

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
            //简单的去重，略微优化一下，对结果不影响
            if (i - 1 >= start && sChars[i] == sChars[i - 1]) {
                continue;
            }
            //准备一个长度-1的数组来装删除一个无效括号后的原数组
            char[] newChar = new char[sChars.length - 1];
            int index = 0;
            for (int k = 0; k < sChars.length; k ++) {
                //除该索引i即我们认为的无效括号位置外，全都添加到新数组中去
                if (k != i) {
                    newChar[index ++] = sChars[k];
                }
            }
            //去掉一个括号，并深入遍历找下一个可能需要删除的地方，并注意此时用的是新数组
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
