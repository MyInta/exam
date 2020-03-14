package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2020/1/6
 * @describe 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 */
public class Q343integerBreak {
    //用数学归纳法发现的规律，可以直接算出来，效率极高，但估计本意是dp题？
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int res = 1;
        int x = n / 3;
        int resume = n % 3;
        if (resume == 2) {
            res *= resume;
        } else if (resume == 1) {
            x --;
            resume += 3;
            res *= resume;
        }
        while (x > 0) {
            res *= 3;
            x --;
        }
        return res;
    }
}
