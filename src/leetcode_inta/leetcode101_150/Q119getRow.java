package leetcode_inta.leetcode101_150;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/27
 * @describe 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class Q119getRow {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex == 0) {
            res.add(1);
            return res;
        } else if (rowIndex == 1) {
            res.add(1);
            res.add(1);
            return res;
        }
        int[][] dp = new int[rowIndex + 1][rowIndex + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= rowIndex; i ++) {
            dp[i][0] = 1;
            for (int j = 1; j < i; j ++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
            dp[i][i] = 1;
        }
        for (int j = 0; j <= rowIndex; j ++) {
            res.add(dp[rowIndex][j]);
        }
        return res;
    }
}
