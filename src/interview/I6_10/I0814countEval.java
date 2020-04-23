package interview.I6_10;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/4/23
 * @describe 给定一个布尔表达式和一个期望的布尔结果 result，
 * 布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 *
 * 示例 1:
 *
 * 输入: s = "1^0|0|1", result = 0
 *
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 *
 * 示例 2:
 *
 * 输入: s = "0&0&0&1^1|0", result = 1
 *
 * 输出: 10
 *
 * 提示：
 *
 *     运算符的数量不超过 19 个
 *
 */
public class I0814countEval {
    private char[] chars;
    private int[][][] dp;
    public int countEval(String s, int result) {
        chars = s.toCharArray();
        //结果为0或者1，长度2
        dp = new int[chars.length][chars.length][2];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solution(0, chars.length - 1, result);
    }
    private int solution(int start, int end, int target) {
        //递归到最细情况，符合目标值时，括号可以取1个
        if (start == end) return chars[start] - '0' == target ? 1 : 0;
        //如果早就计算过，直接返回(start,end)含有的可以的括号数量
        if (dp[start][end][target] != -1) return dp[start][end][target];
        //用于统计符合的括号数
        int sum = 0;
        for (int k = start; k < end; k += 2) {
            char operator = chars[k + 1];
            //左右布尔只存在四种情况 00 01 10 11 我们只要统计可以达成左右加中间操作后目标值的括号数量有多少即可
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    if (opera(i, j, operator) == target) {
                        //上面的左右0或1，并非一定左右存在0或1，而是假定若0或1才能满足当前star end target目标
                        //因此，也有可能发现左右其中有一个或全部都取不到0或1，即solution子递归返回括号数量为0
                        sum += solution(start, k, i) * solution(k + 2, end, j);
                    }
                }
            }
        }
        dp[start][end][target] = sum;
        return sum;
    }
    //获得左右两值和操作符下的最终结果
    private int opera(int val1, int val2, char operator) {
        if (operator == '&') {
            return val1 & val2;
        } else if (operator == '|') {
            return val1 | val2;
        } else {
            //默认三个里面选，剩下就是^
            return val1 ^ val2;
        }
    }
}
