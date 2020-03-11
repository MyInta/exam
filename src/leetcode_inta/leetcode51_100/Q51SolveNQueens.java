package leetcode_inta.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/7/29
 * @describe n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 * 输入: 4
 * 输出: [
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
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class Q51SolveNQueens {
    //记录每一行中，皇后位于的列索引位置
    int[] paths;
    boolean[] lines;
    List<List<String>> ans;
    // /线上
    boolean[] plus;
    // \线上
    boolean[] minus;
    //关于N皇后问题的解答
    private void solution(int idx, int n) {
        //如果idx大于等于n说明皇后们都已经找到所有合适位置，进行存储（遍历到第idx行）
        if (idx >= n) {
            List<String> list = new ArrayList<>(n);
            for (int i = 0;i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == paths[i]) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }
                list.add(sb.toString());
            }
            ans.add(list);
            return;
        }
        //遍历所有可能并去重，分别去掉行重复，斜线重复（正斜线/plus和反斜线\minus）
        for (int i = 0; i < n; i++) {
            //正斜线和反斜线一加一减分别为常值，加上n-1保证为正值
            if (!lines[i] && !plus[idx + i]&& !minus[i - idx + n - 1]) {
                //记录第idx行填充一个皇后，该皇后在第i列
                paths[idx] = i;
                lines[i] = true;
                plus[idx + i] = true;
                minus[i - idx + n - 1] = true;
                solution(idx + 1,n);
                //复原
                lines[i] = false;
                plus[idx + i] = false;
                minus[i - idx + n - 1] = false;
            }
        }
    }


    public List<List<String>> solveNQueens(int n) {
        paths = new int[n];
        lines = new boolean[n];
        ans = new ArrayList<>();
        plus = new boolean[n<<1];
        minus =new boolean[n<<1];
        solution(0, n);
        return ans;
    }


}
