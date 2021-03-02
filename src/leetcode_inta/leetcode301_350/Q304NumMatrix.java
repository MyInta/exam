package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2021/3/2
 * @describe 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * 示例:
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * 说明:
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 */
public class Q304NumMatrix {
    // 这类题目不能傻乎乎一次次累加，要用空间换时间，需要统计每个点到(0,0)形成的矩阵面积
    private int[][] counts;
    private int[][] matrix;
    public Q304NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        buildCounts();
    }

    private void buildCounts() {
        int m = matrix.length;
        if (m == 0) {
            this.counts = new int[0][0];
            return;
        }
        // 预防出现matrix不含元素的情况
        int n = matrix[0].length;
        if (n == 0) {
            this.counts = new int[0][0];
            return;
        }
        this.counts = new int[m][n];
        counts[0][0] = matrix[0][0];
        for (int i = 1; i < m; i++) {
            counts[i][0] = counts[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < n; j++) {
            counts[0][j] = counts[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                counts[i][j] = counts[i - 1][j] + counts[i][j - 1] - counts[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    // 测试用例不够强，在没约定索引值时候，需要考虑一下这个["NumMatrix","sumRegion","sumRegion","sumRegion"]
    //[[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,12,12],[-1,-2,2,4]]
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (counts.length == 0 || counts[0].length == 0 || row1 >= counts.length || col1 >= counts[0].length) {
            return 0;
        }

        row1 = row1 < 0 ? 0 : row1;
        col1 = col1 < 0 ? 0 : col1;
        row2 = row2 >= counts.length ? counts.length - 1 : row2;
        col2 = col2 >= counts[0].length ? counts[0].length - 1 : col2;

        int pre = 0;
        int left = 0;
        int right = 0;
        if (row1 > 0 && col1 > 0) {
            pre = counts[row1 - 1][col1 - 1];
        }
        if (col1 > 0) {
            left = counts[row2][col1 - 1];
        }
        if (row1 > 0) {
            right = counts[row1 - 1][col2];
        }
        return counts[row2][col2] - left - right + pre;
    }
}
