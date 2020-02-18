package tencent.leetcode501_550;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/18
 * @describe 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 */
public class Q516longestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        char[] chars = s.toCharArray();
        //从后往前遍历
        for (int i = chars.length - 1; i >= 0; i --) {
            dp[i][i] = 1;
            //扩张j到右边尽头
            for (int j = i + 1; j < chars.length; j ++) {
                //如果两边相等，那么就是内部最优解+2的长度
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    //否则就是两侧选择一侧消减后的最优解
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][chars.length - 1];
    }

    //如果我改成从前往后遍历呢
    public int longestPalindromeSubseq2(String s) {
        int[][] dp = new int[s.length()][s.length()];
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j ++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i --) {
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][chars.length - 1];
    }

    //能否改成一维数组？
    public int longestPalindromeSubseq3(String s) {
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        Arrays.fill(dp, 1);
        //从后往前遍历
        for (int i = chars.length - 1; i >= 0; i --) {
            int pre = 0;
            //扩张j到右边尽头
            for (int j = i + 1; j < chars.length; j ++) {
                int temp = dp[j];
                //如果两边相等，那么就是内部最优解+2的长度
                if (chars[i] == chars[j]) {
                    dp[j] = pre + 2;
                } else {
                    //否则就是两侧选择一侧消减后的最优解
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = temp;
            }
        }
        return dp[s.length() - 1];
    }
}
