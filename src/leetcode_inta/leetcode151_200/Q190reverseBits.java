package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/23
 * @describe 颠倒给定的 32 位无符号整数的二进制位。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *       因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *       因此返回 3221225471 其二进制表示形式为 10101111110010110010011101101001。
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。
 * 在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
 * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 
 * 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 */
public class Q190reverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        StringBuilder sb = new StringBuilder();
        //不管最后一位是否为0，都得加0，因为1代表符号负，影响二进制转换
        //因为已经操作了一下，遍历剩余31次即可
        for (int i = 0; i < 31; i ++) {
            int x = n & 1;
            sb.append(x);
            n >>= 1;
        }
        String str = sb.toString();
        return (int)Long.parseLong(sb.toString(), 2);
    }
    //直接计算，不需要字符串的转换
    public int reverseBits2(int n) {
        int res = 0;
        //移动n31次，而结果res因为一开始就在第一位，所以也是移动31位即可，最后32是补上数值
        for (int i = 0; i < 31; i ++) {
            res += n & 1;
            res <<= 1;
            n >>= 1;
        }
        res += n & 1;
        return res;
    }

    public int reverseBits3(int n) {
        int res = 0;
        for (int i = 0; i <= 32; i ++) {
            int x = n >> i;
            x = x & 1;
            x = x << (31 - i);
            res |= x;
        }
        return res;
    }
}
