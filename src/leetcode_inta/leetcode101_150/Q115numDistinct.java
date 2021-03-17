package leetcode_inta.leetcode101_150;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/21
 * @describe 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 示例 1:
 * 输入: S = "rabbbit", T = "rabbit"
 * 输出: 3
 * 解释:
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2:
 * 输入: S = "babgbag", T = "bag"
 * 输出: 5
 * 解释:
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class Q115numDistinct {
    // 看了大神解答，突然发现，dp做很简单，就是不容易想到
    public int numDistinct(String s, String t) {
        int res = 0;
        if (s.length() < t.length()) return res;
        // 转化成字符数组方便查找
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int k = 0; k <= s.length(); k ++) {
            dp[k][0] = 1;
        }
        for (int j = 1; j <= t.length(); j ++) {
            for (int i = 1; i <= s.length(); i ++) {
                // 剪枝
                if (i < j) {
                    continue;
                }
                if (sChars[i - 1] == tChars[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    // 59/74 TLE
//    public int numDistinct2(String s, String t) {
//        if (t.length() == 0) {
//            return 1;
//        }
//        counts = new int[s.length()][t.length()];
//        return solution(s, t, s.length() - 1, t.length() - 1);
//    }
//    private int solution(String s, String t, int start1, int start2) {
//        if (start1 < start2 || start2 < 0) {
//            return 0;
//        }
//        int res = 0;
//        if (start2 == 0) {
//            for (int i = start1; i >= 0; i--) {
//                if (s.charAt(i) == t.charAt(0)) {
//                    res++;
//                }
//            }
//            return res;
//        }
//        for (int i = start1; i > 0; i--) {
//            if (s.charAt(i) == t.charAt(start2)) {
//                res += solution(s, t, i - 1, start2 - 1);
//            }
//        }
//        return res;
//    }

    // 3ms 92%
    public int numDistinct2(String s, String t) {
        if (t.length() == 0) {
            return 1;
        }
        counts = new int[s.length()][t.length()];
        for (int[] count : counts) {
            Arrays.fill(count, -1);
        }
        return solution(s, t, s.length() - 1, t.length() - 1);
    }
    private int[][] counts;
    private int solution(String s, String t, int start1, int start2) {
        if (start1 < start2 || start2 < 0) {
            return 0;
        }
        if (counts[start1][start2] != -1) {
            return counts[start1][start2];
        }
        int res = 0;
        if (start2 == 0) {
            for (int i = start1; i >= 0; i--) {
                if (s.charAt(i) == t.charAt(0)) {
                    res++;
                }
            }
            counts[start1][start2] = res;
            return res;
        }
        for (int i = start1; i > 0; i--) {
            if (s.charAt(i) == t.charAt(start2)) {
                res += solution(s, t, i - 1, start2 - 1);
            }
        }
        counts[start1][start2] = res;
        return res;
    }
}
