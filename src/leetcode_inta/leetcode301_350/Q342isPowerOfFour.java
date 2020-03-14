package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2019/11/14
 * @describe 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 16
 * 输出: true
 * 示例 2:
 *
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 */
public class Q342isPowerOfFour {
    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        while (num != 1) {
            if (num % 4 != 0) return false;
            num >>= 2;
        }
        return true;
    }
}
