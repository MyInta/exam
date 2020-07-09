package leetcode_inta.leetcode701_750;

/**
 * @author inta
 * @date 2020/7/1
 * @describe 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 *
 * 说明:
 *
 *     1 <= len(A), len(B) <= 1000
 *     0 <= A[i], B[i] < 100
 *
 */
public class Q718findLength {
    public int findLength(int[] A, int[] B) {
        int a_length = A.length;
        int b_length = B.length;
        int l = 0;
        int r = b_length - 1;
        int res = 0;
        while (l < a_length) {
            int A_cur = l;
            int B_cur = r;
            int k = 0;
            while (A_cur < a_length && B_cur < b_length) {
                if (A[A_cur] == B[B_cur]) {
                    k ++;
                } else {
                    res = Math.max(res, k);
                    k = 0;
                }
                A_cur ++;
                B_cur ++;
            }
            res = Math.max(res, k);
            if (r != 0) {
                r --;
            } else {
                l ++;
            }
        }
        return res;
    }


    //使用dp
    public int findLength2(int[] A, int[] B) {
        int a = A.length, b = B.length, ans = 0;
        int[][] dp = new int[a + 1][b + 1];
        for (int i = a - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
