package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2019/9/23
 * @describe 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 */
public class Q338countBits {
    // 该方法过于蠢，是最简单的思路，效率也由此变差
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            char[] chars = Integer.toBinaryString(i).toCharArray();
            int count = 0;
            for (char c : chars) {
                if (c == '1') {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    // 使用与运算，削减最后一位的1，如n = n和n-1与运算，n二进制最后一位的1会变为0，110100和110011与运算为110000
    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i < res.length; i++) {
            int count = 0;
            int temp = i;
            while (temp != 0) {
                count++;
                temp &= temp - 1;
            }
            res[i] = count;
        }
        return res;
    }

    // 动态方程，思想包括P(x) = P（x/2）+ x&1; P(x) = P(x&x-1) + 1;
    public int[] countBits3(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i >> 1] + (i&1);
        }
        return res;
    }

    public int[] countBits4(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}
