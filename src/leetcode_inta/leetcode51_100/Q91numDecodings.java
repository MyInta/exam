package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/26
 * @describe 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Q91numDecodings {
    // 动态规划，状态转移方程 d[i] = d[i - 2] 和 d[i] = d[i-1] + d[i-2]需要注意的
    // 边界条件 10 20 1-26(不含10 20) 以及 x0(x > 2)的情况
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        // 考虑三位 当前位，上一位和上上位
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '0') {
                if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2')) {
                    // 只能和前一个组合，所以次数继承的上上个
                    dp[i + 1] = dp[i - 1];
                } else {
                    return 0;
                }
            } else if ((cur < '7' && s.charAt(i - 1) == '2') || s.charAt(i - 1) == '1') {
                // 此时达到i + 1位置可以是上一个或者上上个的组合结果
                dp[i + 1] = dp[i - 1] + dp[i];
            } else {
                // 此时不能形成组合，次数继承上一个
                dp[i + 1] = dp[i];
            }
        }
        return dp[s.length()];
    }
}
