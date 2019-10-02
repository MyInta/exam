package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/1
 * @describe 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 */
public class Q7reverse {
    //转化为string和long判断溢出
    public int reverse(int x) {
        StringBuilder str = new StringBuilder();
        str.append(x);
        String res = str.reverse().toString();
        if (x < 0) {
            long temp = Long.valueOf(res.substring(0, res.length() - 1));
            if (temp > Integer.MAX_VALUE - 1) {
                return 0;
            } else {
                return (int) -temp;
            }
        } else {
            long temp = Long.valueOf(res);
            if (temp > Integer.MAX_VALUE) {
                return 0;
            }
            return (int) temp;
        }
    }
    //或者直接使用数学的方法
    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || res == Integer.MAX_VALUE / 10 && pop > 7) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || res == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            x /= 10;
            res = res*10 + pop;
        }
        return res;
    }
}
