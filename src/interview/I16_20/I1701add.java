package interview.I16_20;

/**
 * @author inta
 * @date 2020/4/1
 * @describe 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 *
 *     a, b 均可能是负数或 0
 *     结果不会溢出 32 位整数
 */
public class I1701add {
    //看了大神的思路（虽然也是书本里的基础），顿悟
    public int add(int a, int b) {
        //分别用于保存未进位部分和进位部分
        int sum = 0, carry = 0;
        while (b != 0) {
            //保留未进位部分
            sum = a ^ b;
            carry = (a & b) << 1;
            //然后将两者合并消减进位部分，直到进位为0
            a = sum;
            b = carry;
        }
        return a;
    }
}
