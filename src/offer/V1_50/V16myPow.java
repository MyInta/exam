package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/28
 * @describe 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *  LC50
 */
public class V16myPow {
    //想起冬瓜大佬的思路，指数理解为二进制
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        double abs_x = x < 0 ? -x : x;
        long abs_n = n < 0 ? -(long)n : n;
        double res = 1;
        while (abs_n > 0) {
            if ((abs_n & 1) == 1) res *= abs_x;
            abs_n >>= 1;
            abs_x *= abs_x;
        }
        res = n < 0 ? 1 / res : res;
        return n % 2 == 0 ? res : (x < 0 ? -res : res);
    }
    /*  if (x == 0) return 0;
        if (x == 1) return 1;
        long absN;
        if (n < 0) {
            absN = -n;
            x = 1 / x;
        } else {
            absN = n;
        }
        double res = 1;
        while (absN > 0) {
            if ((absN & 1) == 1) res *= x;
            absN >>= 1;
            x *= x;
        }
        return res;*/
}
