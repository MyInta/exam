package leetcode_inta.leetcode1051_1100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2021/5/29
 * @describe 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 * 示例 1：
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 * 提示：
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 */
public class Q1074numSubmatrixSumTarget {
    // 先计算出(i,j)->(i',j')的矩形和，再双重for循环找子矩形，子矩形面积为dp[i'][j']-dp[i-1][j']-dp[i'][j-1]+dp[i-1][j-1]
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 此时获得矩形和，找子矩形
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= i; k++) {
                    for (int l = 1; l <= j; l++) {
                        int cur = dp[i][j] - dp[k - 1][j] - dp[i][l - 1] + dp[k - 1][l - 1];
                        if (cur == target) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    // 前缀树+哈希
    public int numSubmatrixSumTarget2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int endI = i; endI < m; endI++) {
                for (int j = 0; j < n; j++) {
                    sum[j] += matrix[endI][j];
                }
                res += getSubmatrix(sum, target);
            }
        }
        return res;
    }

    private int getSubmatrix(int[] sum, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int pre = 0;
        // 默认自身数量为1
        map.put(0, 1);
        for (int num : sum) {
            pre += num;
            if (map.containsKey(pre - target)) {
                count += map.get(pre - target);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
