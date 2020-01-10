package tencent.leetcode401_450;

/**
 * @author inta
 * @date 2020/1/8
 * @describe 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 *
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 *
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 * 示例 1:
 *
 * n = 5
 *
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * 因为第三行不完整，所以返回2.
 * 示例 2:
 *
 * n = 8
 *
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 * 因为第四行不完整，所以返回3.
 */
public class Q441arrangeCoins {
    public int arrangeCoins(int n) {
        if (n == 0) return 0;
        int i = 1;
        for (; i <= n; i ++) {
            if ((1 + i) / 2.0 == n * 1.0 / i) {
                return i;
            } else if ((1 + i) / 2.0 > n * 1.0 / i) {
                return i - 1;
            }
        }
        return -1;
    }

    //改进上诉数学方式，直接计算n内最大x即可 x <= (-1 + 根号(1+8n)) / 2
    public int arrangeCoins2(int n) {
        return (int)Math.floor((-1 + Math.sqrt(1 + 8.0 * n)) / 2);
    }
}
