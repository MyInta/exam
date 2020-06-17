package leetcode_inta.leetcode1001_1050;

/**
 * @author inta
 * @date 2020/6/17
 * @describe 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 *
 *
 * 示例：
 *
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 *
 *
 * 提示：
 *
 *     2 <= A.length <= 50000
 *     1 <= A[i] <= 1000
 */
public class Q1014maxScoreSightseeingPair {
    //暴力？
    //大佬给了思路，拆分为A[i] + i + A[j] - j,然后前面A[i]+i取一路走来最大的即可
    public int maxScoreSightseeingPair(int[] A) {
        int pre = A[0];
        int res = -1;
        for (int j = 1; j < A.length; j++) {
            res = Math.max(res, pre + A[j] - j);
            pre = Math.max(pre, A[j] + j);
        }
        return res;
    }
}
