package tencent.leetcode501_550;

/**
 * @author inta
 * @date 2020/2/20
 * @describe 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 *
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 */
public class Q542updateMatrix {
    //大神提供的思路，左上到右下遍历一遍，再右下到左上遍历一遍，取两者最小值
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (matrix[i][j] > 0 ) {
                    int l = 10000, u = 10000;
                    if (i > 0) l = matrix[i - 1][j];
                    if (j > 0) u = matrix[i][j - 1];
                    matrix[i][j] = Math.min(l, u) + 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i --) {
            for (int j = m - 1; j >= 0; j --) {
                if (matrix[i][j] > 0) {
                    int r = 10000, d = 10000;
                    if (i < n - 1) r = matrix[i + 1][j];
                    if (j < m - 1) d = matrix[i][j + 1];
                    matrix[i][j] = Math.min(matrix[i][j], Math.min(r, d) + 1);
                }
            }
        }
        return matrix;
    }
}
