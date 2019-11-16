package tencent.leetcode301_350;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/11/16
 * @describe 编写一段程序来查找第 n 个超级丑数。
 *
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 *
 * 示例:
 *
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 说明:
 *
 * 1 是任何给定 primes 的超级丑数。
 *  给定 primes 中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000 。
 * 第 n 个超级丑数确保在 32 位有符整数范围内。
 */
public class Q313nthSuperUglyNumber {
    //使用一个优先队列来维护最小的值 TLE   - -!
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> prior = new PriorityQueue<>();
        for (int prime : primes) {
            prior.add((long)prime);
        }
        //倒着记录，第1个为第n个丑数
        long[] res = new long[n];
        res[n - 1] = 1;
        //因为1是默认的首个超级丑数
        n --;
        while (n > 0) {
            long first = prior.poll();
            res[n - 1] = first;
            n --;
            //将取出来的最小值乘以因子后再放入优先队列中，让其进行维护
            for (int prime : primes) {
                if (!prior.contains((long)(first * prime))) {
                    prior.add((long)(first * prime));
                }
            }
        }
        return (int)res[0];
    }

    //使用动态规划解答
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] i_primes = new int[primes.length];
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i ++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j ++) {
                min = Math.min(min, primes[j] * dp[i_primes[j]]);
            }
            for (int j = 0; j < primes.length; j ++) {
                if (min == primes[j] * dp[i_primes[j]]) i_primes[j] ++;
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }
}
