package interview.I1_5;

/**
 * @author inta
 * @date 2020/4/21
 * @describe 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 *
 * 示例1:
 *
 *  输入：num = 2（或者0b10）
 *  输出：[4, 1] 或者（[0b100, 0b1]）
 *
 * 示例2:
 *
 *  输入：num = 1
 *  输出：[2, -1]
 *
 * 提示:
 *
 *     num的范围在[1, 2147483647]之间；
 *     如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 *
 */
public class I0504findClosedNumbers {
    //位运算总是让我有点心虚。看了题解再来做的
    public int[] findClosedNumbers(int num) {
        int up = num + 1, down = num - 1;
        int target = getCount(num);
        while (up > 0 && getCount(up) != target) {
            up ++;
        }
        while (down > 0 && getCount(down) != target) {
            down --;
        }
        return new int[]{up, down};
    }
    //统计num位上1的数量
    private int getCount(int num) {
        int res = 0;
        while (num != 0) {
            num &= num - 1;
            res ++;
        }
        return res;
    }
}
