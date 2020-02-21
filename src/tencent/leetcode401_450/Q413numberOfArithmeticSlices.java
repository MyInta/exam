package tencent.leetcode401_450;

/**
 * @author inta
 * @date 2020/2/21
 * @describe 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，以下数列为等差数列:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 *
 * 1, 1, 2, 5, 7
 *  
 *
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。
 *
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 *
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 *
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 *
 *  
 *
 * 示例:
 *
 * A = [1, 2, 3, 4]
 *
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 */
public class Q413numberOfArithmeticSlices {
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        int res = 0;
        //保存一个距离值
        int dis = A[1] - A[0];
        int left = 0, right = 1;
        //遍历两个指针
        while (right < A.length) {
            //如果遇到新的距离，判断区间长度，考虑是否添加，然后更新指针位置
            if (A[right] - A[right - 1] != dis) {
                int n = right - left;
                if (n > 2) res += sum(n);
                left = right - 1;
                dis = A[right] - A[left];
            }
            right ++;
        }
        //最后考虑可能末端没有出现不同距离情况下，最后一段区间考虑是否添加
        if (right - left > 2) res += sum(right - left);
        return res;
    }
    private int sum(int n) {
        //根据奇偶选择哪个先除，为了防止可能的溢出
        return (n & 1) == 1 ? (n - 1) / 2 * (n - 2) : (n - 2) / 2 * (n - 1);
    }

    //虽然我自己直接想到了数学法，但还是写写官解用到的dp吧，毕竟尊重下“算法”
    public int numberOfArithmeticSlices2(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < A.length; i ++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }
}
