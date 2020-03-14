package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2019/11/4
 * @describe 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 *
 * 示例 1:
 *
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 *
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 *
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 *  示例 4:
 *
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 *
 */
public class Q693hasAlternatingBits {
    //这是递归 移位来找寻是否两两不等
    public boolean hasAlternatingBits(int n) {
        if (n == 0 || n == 1) return true;
        if ((n & 1) == ((n >> 1) & 1)) return false;
        n >>= 1;
        return hasAlternatingBits(n);
    }

    //也可以用^来找不同
    public boolean hasAlternatingBits2(int n) {
        if (n == 0 || n == 1) return true;
        //如果符合题意，那么n将变为二进制全为1的存在
        n = n ^ (n >> 1);
        return (n & (long)(n + 1)) == 0 ? true : false;
    }
}
