package tencent.leetcode51_100;

/**
 * @author inta
 * @date 2020/1/7
 * @describe n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class Q52totalNQueens {
    //核心，保存行记录，列记录，斜线1记录i+j，斜线2记录j - i + n
    public int totalNQueens(int n) {
        //记录行号
        boolean[] rows = new boolean[n];
        //记录列号
        boolean[] cols = new boolean[n];
        //记录斜线1号
        boolean[] xie_one = new boolean[2 * n];
        //记录斜线2号
        boolean[] xie_two = new boolean[2 * n];
        dfs(rows, cols, xie_one, xie_two, n, 0, 0);
        return res;
    }

    private int res = 0;

    private void dfs(boolean[] rows, boolean[] cols, boolean[] xie_one, boolean[] xie_two, int n, int i, int j) {
        //当处于第n+1行，即索引越界为n时候，说明能顺利到末行，可以考虑添加一波
        if (i == n) {
            res ++;
            return;
        }
        //如果碰到列界限，直接返回
        if (j == n) return;
        //当横竖斜都没有被占领情况下，进入下一层，列从头开始考虑起
        if (!rows[i] && !cols[j] && !xie_one[i + j] && !xie_two[j - i + n]) {
            rows[i] = true;
            cols[j] = true;
            xie_one[i + j] = true;
            xie_two[j - i + n] = true;
            //进入下一行，并将列重置为0
            dfs(rows, cols, xie_one, xie_two, n, i + 1, 0);
            //回退
            rows[i] = false;
            cols[j] = false;
            xie_one[i + j] = false;
            xie_two[j - i + n] = false;

        }
        //如果上述(i,j)判断过了，那么我们下一步判断(i,j+1)
        dfs(rows, cols, xie_one, xie_two, n, i, j + 1);
    }
}
