package leetcode_inta.leetcode451_500;

/**
 * @author inta
 * @date 2019/9/21
 * @describe 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 231.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class Q461hammingDistance {
    public int hammingDistance(int x, int y) {
        int sum = 0;
        String res = Integer.toBinaryString(x ^ y);
        for (char c : res.toCharArray()) {
            if (c == '1') {
                sum++;
            }
        }
        return sum;
    }

    public int hammingDistance2(int x, int y) {
        int target = x ^ y;
        int res = 0;
        while (target > 0) {
            if ((target & 1) == 1) {
                res++;
            }
            target >>= 1;
        }
        return res;
    }

    public int hammingDistance3(int x, int y) {
        int res = 0;
        while (x > 0 || y > 0) {
            res += (x & 1) ^ (y & 1);
            x >>= 1;
            y >>= 1;
        }
        return res;
    }
}
