package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/9/27
 * @describe 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * 示例 2:
 *
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * 示例 3:
 *
 * 输入: 218
 * 输出: false
 *
 */
public class Q231isPowerOfTwo {
    //方法一：自己想的或操作
    public boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        int temp = 1;
        temp <<= Integer.toBinaryString(n).length() - 1;
        return temp == n;
    }
    //与操作
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }

}
