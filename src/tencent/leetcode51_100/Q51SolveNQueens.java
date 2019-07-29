package tencent.leetcode51_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int[] path;
    public List<List<String>> ans;
    public boolean[] line;
    public boolean[] plus;
    public boolean[] minus;
    public void solve(int idx,int n){
        if(idx>=n){
            List<String> chess = new ArrayList<String>();
            for(int i=0;i<n;i++){
                String tmp = "";
                for(int j=0;j<n;j++){
                    if(j==path[i]){
                        tmp +="Q";
                    }else{
                        tmp +=".";
                    }
                }
                chess.add(tmp);
            }
            ans.add(chess);
            return;
        }
        //去重，去行与斜线
        for(int i=0;i<n;i++){
            if(!line[i]&&!plus[i+idx]&&!minus[idx-i+n-1]){
                path[idx] = i;
                line[i] = true;
                plus[i+idx] = true;
                minus[idx-i+n-1] = true;

                solve(idx+1,n);
                //复原
                line[i] = false;
                plus[i+idx] = false;
                minus[idx-i+n-1] = false;
            }
        }

    }

    public List<List<String>> solveNQueens(int n) {
        path = new int[n];
        ans = new ArrayList<>();
        line = new boolean[n];
        plus = new boolean[(n<<1)];
        minus = new boolean[(n<<1)];
        solve(0,n);
        return ans;
    }

}
