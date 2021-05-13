package leetcode_inta.leetcode1251_1300;

/**
 * @author inta
 * @date 2021/5/13
 * @describe 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * 示例 1：
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * 示例  2：
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * 示例 3：
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 * 提示：
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 */
public class Q1269numWays {
    // dp题，dp[i][j]表示数组i索引位置，走j步拥有的方案数
    // 状态转移方程 i索引走j步，其步数是i索引走j-1步消耗一次移动到达 或者是i索引j+1步原地不动消耗一次到达
    // dp[i][j] = dp[i - 1][j - 1] + dp[i + 1][j - 1] + dp[i][j - 1]
    private int mod = 1_000_000_007;
    public int numWays(int steps, int arrLen) {
        // 最远j不可能超过steps / 2，要考虑能折返
        int maxRight = Math.min(steps / 2, arrLen - 1);
        int[][] dp = new int[maxRight + 1][steps + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= steps; j++) {
            for (int i = 0; i <= maxRight; i++) {
                dp[i][j] = dp[i][j - 1];
                if (i > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }
                if (i < maxRight) {
//                    System.out.println(arrLen + "-" + i + "-" + maxRight + "-" + j);
                    dp[i][j] = (dp[i][j] + dp[i + 1][j - 1]) % mod;
                }
            }
        }
        return dp[0][steps];
    }
}
