package interview.I1_5;

/**
 * @author inta
 * @date 2020/4/3
 * @describe 配对交换。编写程序，交换某个整数的奇数位和偶数位，
 * 尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
 *
 * 示例1:
 *
 *  输入：num = 2（或者0b10）
 *  输出 1 (或者 0b01)
 *
 * 示例2:
 *
 *  输入：num = 3
 *  输出：3
 *
 * 提示:
 *
 *     num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
 *
 */
public class I0507exchangeBits {
    public int exchangeBits(int num) {
        int ans = 0;
        for (int i = 0; i < 32; i += 2) {
            int a = num & (1 << i);
            int b = num & (1 << (i + 1));
            ans |= (a << 1);
            ans |= (b >> 1);
        }
        return ans;
    }
}
