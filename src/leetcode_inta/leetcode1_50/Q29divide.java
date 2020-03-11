package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/28
 * @describe 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 */
public class Q29divide {
    public int divide(int dividend, int divisor) {
        int result = 0;
        if (dividend == 0) return 0;
        boolean flag = (dividend > 0) ^ (divisor > 0);
        if (dividend > 0) {
            dividend = - dividend;
        }
        if (divisor > 0) {
            divisor = - divisor;
        }
        while (dividend <= divisor) {
            int temp_result = -1;
            int temp_divisor = divisor;
            while (dividend <= (temp_divisor << 1)) {
                if (temp_divisor <= (Integer.MIN_VALUE >> 1)) break;
                //temp_result和temp_divisor是相伴随的，区别在于后者多乘了一个被除数，所以可理解为前者在记录可被除的数量
                temp_result = temp_result << 1;
                temp_divisor = temp_divisor << 1;
            }
            dividend = dividend - temp_divisor;
            result += temp_result;
        }
        //说明解为整数，需要将原先设定的负值反转
        if (!flag) {
            //溢出处理
            if (result <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
            result = - result;
        }
        return result;
    }
}
