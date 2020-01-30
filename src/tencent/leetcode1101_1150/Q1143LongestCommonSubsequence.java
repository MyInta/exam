package tencent.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/9/3
 * @describe 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 示例 1:
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *  
 *
 * 提示:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 *
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

    private int longestCommonSubsequence2(String text1, String text2) {
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

    //上面的解法很简单，但难在于想到状态转移方程，另外，如果空间要优化为一维，需要进一步思考
    private int longestCommonSubsequence3(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int maxL, minL;
        char[] maxC, minC;
        if (len1 >= len2) {
            maxL = len1;
            minL = len2;
            maxC = text1.toCharArray();
            minC = text2.toCharArray();
        } else {
            maxL = len2;
            minL = len1;
            maxC = text2.toCharArray();
            minC = text1.toCharArray();
        }
        int[] dp = new int[minL + 1];
        for (int i = 1; i <= maxL; i ++) {
            //保存从index为i位置下，遍历每个j索引位置找到的公共部分数量
            int count = 0;
            for (int j = 1; j <= minL; j ++) {
                //保留原先j位置的统计到的公共部分数量，也就是上一轮i遍历过统计到的dp[j]旧值
                int pre = dp[j];
                if (maxC[i - 1] == minC[j - 1]) {
                    //找到一个就让公共部分数量增加
                    dp[j] = count + 1;
                } else {
                    //若不相等，则该位置dp应该取本身和前一位置中值最大的
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                //要遍历下一个j了，需要将统计值刷新为之前一轮i遍历到dp[j]的旧值
                count = pre;
            }
        }
        return dp[minL];
    }
}
