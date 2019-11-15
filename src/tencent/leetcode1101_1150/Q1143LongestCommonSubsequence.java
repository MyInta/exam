package tencent.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/9/3
 * @describe Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters.
 * (eg, "ace" is a subsequence of "abcde" while "aec" is not).
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * If there is no common subsequence, return 0.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 */
public class Q1143LongestCommonSubsequence {
    int[][] res;
    public int longestCommonSubsequence(String text1, String text2) {
        res = new int[text1.length()+1][text2.length()+1];
        return solution(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    private int solution(String text1, String text2, int i, int j) {
        if (i < 0||j < 0) {
            return 0;
        }
        if (res[i+1][j+1] != 0) {
            return res[i+1][j+1];
        }
        if (text1.charAt(i) == text2.charAt(j)) {
            res[i][j] = solution(text1, text2, i-1, j-1);
            res[i+1][j+1] = res[i][j] + 1;
            return res[i+1][j+1];
        }
        res[i][j+1] = solution(text1, text2, i-1, j);
        res[i+1][j] = solution(text1, text2, i, j-1);
        return Math.max(res[i][j+1], res[i+1][j]);
    }

    private class Q1143LongestCommonSubsequence2 {
        public int longestCommonSubsequence(String text1, String text2) {
            int text1_length = text1.length();
            int text2_length = text2.length();
            int[][] res = new int[text1_length+1][text2_length+1];
            for (int i = 1;i <= text1_length; i++) {
                for (int j = 1;j <= text2_length; j++) {
                    if (text1.charAt(i-1) == text2.charAt(j-1)) {
                        res[i][j] = res[i-1][j-1] +1;
                    } else {
                        res[i][j] = Math.max(res[i-1][j], res[i][j-1]);
                    }
                }
            }
            return res[text1_length][text2_length];
        }
    }
}
