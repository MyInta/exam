package leetcode_inta.leetcode201_250;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/30
 * @describe 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
public class Q204countPrimes {
    public int countPrimes(int n) {
        if (n == 0 || n == 1 || n == 2) return 0;
        int res = 1;
        for (int i = 3; i < n; i += 2) {
            if (isPrime(i)) res ++;
        }
        return res;
    }
    private boolean isPrime(int num) {
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    //正向
    public int countPrimes3(int n) {
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);
        for (int i = 2; i * i < n; i ++) {
            if (isPrimes[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrimes[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            if (isPrimes[i]) count ++;
        }
        return count;
    }

    //看了题解后的优化(反向)
    public int countPrimes2(int n) {
//        if (n == 0 || n == 1) {
//            return 0;
//        }
        boolean[] isNotPrimes = new boolean[n];
//        isNotPrimes[0] = true;
//        isNotPrimes[1] = true;
        for (int i = 2; i * i < n; i ++) {
            if (!isNotPrimes[i]) {
                for (int j = i * i; j < n; j += i) {
                    //找到所有以i为因子的非质数，并标记
                    isNotPrimes[j] = true;
                }
            }
        }
        int res = 0;
//        for (boolean flag : isNotPrimes) {
//            if (!flag) res ++;
//        }
        for (int i = 2; i < n; i ++) {
            if (!isNotPrimes[i]) res ++;
        }
        return res;
    }
}
