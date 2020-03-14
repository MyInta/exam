package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/25
 * @describe 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */
public class Q63uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[n - 1][m - 1] == 1 || obstacleGrid[0][0] == 1) return 0;
        obstacleGrid[n - 1][m - 1] = -1;
        for (int i = n - 1; i >= 0; i --) {
            if (obstacleGrid[i][m - 1] == 1) break;
            obstacleGrid[i][m - 1] = -1;
        }
        for (int j = m - 1; j >= 0; j --) {
            if (obstacleGrid[n - 1][j] == 1) break;
            obstacleGrid[n - 1][j] = -1;
        }
        for (int i = n - 2; i >= 0; i --) {
            for (int j = m - 2; j >= 0; j --) {
                if (obstacleGrid[i][j] == 1) continue;
                if (obstacleGrid[i][j + 1] != 1) {
                    obstacleGrid[i][j] += obstacleGrid[i][j + 1];
                }
                if (obstacleGrid[i + 1][j] != 1) {
                    obstacleGrid[i][j] += obstacleGrid[i + 1][j];
                }
            }
        }
        return -obstacleGrid[0][0];
    }
}
