package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2020/1/2
 * @describe 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * 说明：不要使用任何内置的库函数，如  sqrt。
 *
 * 示例 1：
 *
 * 输入：16
 * 输出：True
 * 示例 2：
 *
 * 输入：14
 * 输出：False
 *
 */
public class Q367isPerfectSquare {
    //核心在于牛顿迭代公式 x = (x + f(x)/x)/2
    public boolean isPerfectSquare(int num) {
        //排除非整数的情况
        if (num <= 0) return false;
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        //此时跳出的x为num的开根接近值，用强转int可以去掉尾数
        return num == (int)x * x;
    }
}
