package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/28
 * @describe 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 *
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 *
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 */
public class Q176trailingZeroes {
    //就是在记录有多少个因子5
    public int trailingZeroes(int n) {
        int res = 0;
        //题目测试的到，0不算尾数有1个0
        //        if (n == 0) return 1;
        while (n / 5 != 0) {
            //已知每5层会有一个类似25的有两个5因子的数，得到以下代码
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}
