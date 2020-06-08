package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2020/6/8
 * @describe 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 *
 * 示例:
 *
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 *
 */
public class Q357countNumbersWithUniqueDigits {
    //这是一道数学题目，排列组合个数，考虑首位不为0，所以原本的10*9*8*... ...被改为9*9*8*...
    //f(0)=1,f(1)=9+f(0),f(2)=9*9+f(1),f(3)=9*9*8+f(2),f(4)=9*9*8*7+f(3)
    public int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 9, temp = i;
            while (temp > 1) {
                temp --;
                sum *= 10 - temp;
            }
            dp[i] = sum + dp[i - 1];
        }
        return dp[n];
    }
}
