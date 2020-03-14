package leetcode_inta.leetcode901_950;

/**
 * @author inta
 * @date 2020/1/25
 * @describe 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
 *
 * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："())"
 * 输出：1
 * 示例 2：
 *
 * 输入："((("
 * 输出：3
 * 示例 3：
 *
 * 输入："()"
 * 输出：0
 * 示例 4：
 *
 * 输入："()))(("
 * 输出：4
 *  
 *
 * 提示：
 *
 * S.length <= 1000
 * S 只包含 '(' 和 ')' 字符。
 */
public class Q921minAddToMakeValid {
    //这道题目，中等题？？？？指针遍历，统计左右括号数量即可
    public int minAddToMakeValid(String S) {
        int res = 0;
        int left = 0, right = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                if (right > left) {
                    //如果在添加左括号之前，发现右括号数量多，就需要补上这个数量的左括号
                    res += right - left;
                    right = 0;
                    left = 0;
                }
                left ++;
            } else if (c == ')') {
                right ++;
            }
        }
        //最后需要考虑残余括号情况
        res += Math.abs(right - left);
        return res;
    }

    //虽然我的执行效率为0ms 超越百分百，但是看了官解，发现人家写得非常精简易懂，
    // 学习下这种可读性强的,而且测试后，发现效率也极高
    public int minAddToMakeValid2(String S) {
        int res = 0, count = 0;
        for (char c : S.toCharArray()) {
            count += c == '(' ? 1 : -1;
            if (count == -1) {
                res ++;
                //count ++;
                count = 0;
            }
        }
        return res + count;
    }
}
