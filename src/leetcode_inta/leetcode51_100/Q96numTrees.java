package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/9
 * @describe 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q96numTrees {
    public int numTrees(int n) {
        //思路是遍历所有节点作为起始节点
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i ++) {
            for (int j = 1; j <= i; j ++) {
                dp[i] += dp[j - 1]*dp[i - j];
            }
        }
        return dp[n];
    }
}
