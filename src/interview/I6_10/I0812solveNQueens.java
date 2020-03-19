package interview.I6_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/19
 * @describe 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，
 * 其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 *
 * 注意：本题相对原题做了扩展
 *
 * 示例:
 *
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class I0812solveNQueens {

    //记录每一行中皇后所在列索引信息
    private int[] paths;
    //记录该列是否被占领
    private boolean[] cols;
    //表示 \ y-x 为常值
    private boolean[] lineOne;
    //表示 / y+x为常值
    private boolean[] lineTwo;
    private List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        paths = new int[n];
        cols = new boolean[n];
        lineOne = new boolean[2 * n];
        lineTwo = new boolean[2 * n];
        res = new ArrayList<>();
        dfs(n, 0);
        return res;
    }
    private void dfs(int n, int cur) {
        if (cur == n) {
            //当遍历到最后一行时候考虑添加
            List<String> lists = new ArrayList<>();
            StringBuilder sb;
            for (int path : paths) {
                sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (path == i) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                lists.add(sb.toString());
            }
            res.add(lists);
            return;
        }
        for (int i = 0; i < n; i++) {
            //如果在斜线上，就是矛盾的，我们去遍历下一个
            if (cols[i] || lineOne[cur - i + n] || lineTwo[cur + i]) continue;
            //把该行皇后所在位置定位为i列
            paths[cur] = i;
            cols[i] = true;
            lineOne[cur - i + n] = true;
            lineTwo[cur + i] = true;
            //找下一行的皇后
            dfs(n, cur + 1);
            //回溯
            cols[i] = false;
            lineOne[cur - i + n] = false;
            lineTwo[cur + i] = false;
        }
    }
}
