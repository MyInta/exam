package interview.I6_10;

/**
 * @author inta
 * @date 2020/3/9
 * @describe 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 *
 * 示例1:
 *
 *  输入：A = 1, B = 10
 *  输出：10
 * 示例2:
 *
 *  输入：A = 3, B = 4
 *  输出：12
 * 提示:
 *
 * 保证乘法范围不会溢出
 */
public class I0805multiply {
    //乘法的本质是一个数的累加
    public int multiply(int A, int B) {
        int a = A > B ? A : B;
        int b = A > B ? B : A;
        //此时a > b
        int res = 0;
        while (b > 0) {
            res += a;
            b --;
        }
        return res;
    }
}
