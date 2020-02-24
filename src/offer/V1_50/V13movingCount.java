package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/23
 * @describe 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的【数位之和】大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 */
public class V13movingCount {
    public int movingCount(int m, int n, int k) {
        res = 0;
        dfs(0, 0, m, n, k, new boolean[m][n]);
        return res;
    }
    private int res;
    private void dfs(int r, int c, int m, int n, int k, boolean[][] visited) {
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) return;
        visited[r][c] = true;
        if (isValid(r, c) > k) return;
        res ++;
        dfs(r + 1, c, m, n, k, visited);
        dfs(r - 1, c, m, n, k, visited);
        dfs(r, c + 1, m, n, k, visited);
        dfs(r, c - 1, m, n, k, visited);
    }
    private int isValid(int r, int c) {
        int sum = 0;
        while (r > 0) {
            sum += r % 10;
            r /= 10;
        }
        while (c > 0) {
            sum += c % 10;
            c /= 10;
        }
        return sum;
    }
}
