package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/11
 * @describe 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 */
public class Q10isMatch {
    //使用递归思想
    public boolean isMatch(String s, String p) {
        //如果匹配字符为空，就看被匹配字符是否也为空，至少要有patter存在
        if (p.isEmpty()) return s.isEmpty();
        //如果不考虑*，所有字符原先只要挨个遍历比较即可，比较前需要排除s为空的情况
        boolean match_one = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        //当匹配字符长度大于等于2的时候，才考虑为*时候，有两条分支，一条是*为0，p直接跳过两个，s不动;另一条p不动，动s
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (match_one && isMatch(s.substring(1), p));
        } else {
            //如果没有*的顾虑，就是简单的字符挨个遍历，剩下的进入递归
            return match_one && isMatch(s.substring(1), p.substring(1));
        }
    }
    //使用动态规划思想
    private int[][] memo;
    public boolean isMatch2(String s, String p) {
        memo = new int[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }
    private boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] == 1) {
            return true;
        }
        boolean ans;
        if (j == p.length()) {
            ans = i == s.length();
        } else {
            //注意括号，因为与和或优先级一样的
            boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2, s, p) || (first && dp(i + 1, j, s, p));
            } else {
                ans = first && dp(i + 1, j + 1, s, p);
            }
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }

    public boolean isMatch3(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i --) {
            for (int j = p.length() - 1; j >= 0; j --) {
                boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (first && dp[i + 1][j]);
                } else {
                    dp[i][j] = first && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
