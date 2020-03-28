package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/28
 * @describe 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 *
 * 示例1:
 *
 *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 *  输出：2
 *
 * 示例2:
 *
 *  输入：A = 1，B = 2
 *  输出：2
 *
 * 提示:
 *
 *     A，B范围在[-2147483648, 2147483647]之间
 *
 */
public class I0506convertInteger {
    public int convertInteger(int A, int B) {
        int num = A ^ B;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (((num >> i) & 1) == 1) res ++;
        }
        return res;
    }

    //官解建议使用num & (num - 1)来操作
    public int convertInteger2(int A, int B) {
        int res = 0;
        int num = A ^ B;
        while (num != 0) {
            //该操作是去除最后一位非零位
            num &= num - 1;
            res ++;
        }
        return res;
    }

}
