package tencent.leetcode901_950;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/11/6
 * @describe 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
 *
 * 在此过程之后，我们得到一些数组 B。
 *
 * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1], K = 0
 * 输出：0
 * 解释：B = [1]
 * 示例 2：
 *
 * 输入：A = [0,10], K = 2
 * 输出：6
 * 解释：B = [2,8]
 * 示例 3：
 *
 * 输入：A = [1,3,6], K = 3
 * 输出：3
 * 解释：B = [4,6,3]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 *
 */
public class Q910smallestRangeII {
    public int smallestRangeII(int[] A, int K) {
        //先把数组排序
        Arrays.sort(A);
        //然后，寻找到最小点与最大点之间的平衡
        int res = A[A.length - 1] - A[0];
        //可能的最小的值
        int addK = A[0] + K;
        //可能的最大的值
        int delK = A[A.length - 1] - K;
        //i相当于指针遍历，指针左边为+k，右边-k，当前状态最小值除了首位，还可能是指针所在位置-k，
        // 可能的最大值为最右边和指针右边-k
        for (int i = 1; i < A.length; i ++) {
            int minK = Math.min(addK, A[i] - K);
            int maxK = Math.max(delK, A[i - 1] + K);
            res = Math.min(Math.abs(maxK - minK), res);
        }
        return res;
    }
}
