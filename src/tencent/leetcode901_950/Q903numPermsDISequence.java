package tencent.leetcode901_950;

/**
 * @author inta
 * @date 2019/11/20
 * @describe 我们给出 S，一个源于 {'D', 'I'} 的长度为 n 的字符串 。（这些字母代表 “减少” 和 “增加”。）
 * 有效排列 是对整数 {0, 1, ..., n} 的一个排列 P[0], P[1], ..., P[n]，使得对所有的 i：
 *
 * 如果 S[i] == 'D'，那么 P[i] > P[i+1]，以及；
 * 如果 S[i] == 'I'，那么 P[i] < P[i+1]。
 * 有多少个有效排列？因为答案可能很大，所以请返回你的答案模 10^9 + 7.
 *
 *  
 *
 * 示例：
 *
 * 输入："DID"
 * 输出：5
 * 解释：
 * (0, 1, 2, 3) 的五个有效排列是：
 * (1, 0, 3, 2)
 * (2, 0, 3, 1)
 * (2, 1, 3, 0)
 * (3, 0, 2, 1)
 * (3, 1, 2, 0)
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * S 仅由集合 {'D', 'I'} 中的字符组成。
 *
 */
public class Q903numPermsDISequence {
    //关键在于考虑最后一位j，每次判断D或者I之后，添加元素，当D(>)时候，添加<=j的元素，并且前面大于等于所添加元素的都+1
    //这样可以保持前面元素的大小关系不变，且元素数量不变，
    // 如1032+一位（0-2内选）-->21430 20431 10432，反之添>j 减大于等于j的元素
    public int numPermsDISequence(String S) {
        int len = S.length();
        int[][] dp = new int[len + 1][len + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i ++) {
            for (int j = 0; j <= i; j ++) {
                if (S.charAt(i - 1) == 'D') {
                    for (int k = j; k < i; k ++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 1_000_000_007;
                    }
                } else {
                    for (int k = 0; k < j; k ++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 1_000_000_007;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= len; i ++) {
            res = (res + dp[len][i]) % 1_000_000_007;
        }
        return res;
    }
}
