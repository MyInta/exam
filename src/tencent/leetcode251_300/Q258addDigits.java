package tencent.leetcode251_300;

/**
 * @author inta
 * @date 2019/12/7
 * @describe 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * 示例:
 *
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 *
 */
public class Q258addDigits {
    //先暴力求解下
    public int addDigits(int num) {
        if (num < 10) return num;
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return addDigits(res);
    }

    public int addDigits2(int num) {
        return 1 + (num - 1) % 9;
    }
    //最容易让我理解的解法
    //N=a*10^x + b*10^(x - 1) + ... + k*10^1 + m*10^0
    //N_change = a + b + ... + k + m
    //N-N_change = 9[x-1个]*a + 9[x -2个]*b + ... + 9*k
    // 得到的是9的倍数,所以我们只要找到i(1-9)使得N-i刚好可以被9整除掉，那么i即为最终留下的所求N_change
    public int addDigits3(int num) {
        for (int i = 1; i < 9; i ++) {
            if ((num - i) % 9 == 0) return i;
        }
        //可能num为0
        return num == 0 ? 0 : 9;
    }
}
