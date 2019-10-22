package tencent.leetcode351_400;

/**
 * @author inta
 * @date 2019/10/22
 * @describe 不使用运算符 + 和 - ，计算两整数 ​a 、b 之和。
 *
 * 示例 1:
 *
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 *
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * a + b 的问题拆分为 (a 和 b 的无进位结果) + (a 和 b 的进位结果)
 * 无进位加法使用异或运算计算得出
 * 进位结果使用与运算和移位运算计算得出
 * 循环此过程，直到进位为 0
 *
 */
public class Q371getSum {
    public int getSum(int a, int b) {
        while (b != 0) {
            int jin = (a & b) << 1;
            a = a ^ b;
            b = jin;
        }
        return a;
    }


    //    public int getSum(int a, int b) {
    //        int tempA = a ^ b;
    //        int tempB = a & b;
    //        while (tempB != 0) {
    //            a = tempA;
    //            b = tempB << 1;
    //            tempA = a ^ b;
    //            tempB = a & b;
    //        }
    //        return tempA;
    //    }


    //    public int getSum(int a, int b) {
    //        int tempA = a ^ b;
    //        int tempB = a & b;
    //        while (tempB != 0) {
    //            b = tempB << 1;
    //            tempB = tempA & b;
    //            tempA = tempA ^ b;
    //        }
    //        return tempA;
    //    }

}
