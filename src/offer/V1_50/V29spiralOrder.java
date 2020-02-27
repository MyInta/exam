package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/27
 * @describe 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *  LC54
 */
public class V29spiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int n = matrix.length;
        int m = matrix[0].length;
        int[] res = new int[n * m];
        int index = 0;
        int u = 0, r = m - 1, d = n - 1, l = 0;
        while (u <= d && l <= r) {
            //考虑此时u==d? l==r?
            if (u == d && l == r) {
                res[index] = matrix[u][l];
                break;
            }
            if (u == d && l < r) {
                for (int j = l; j <= r; j ++) {
                    res[index ++] = matrix[u][j];
                }
                break;
            }
            if (u < d && l == r) {
                for (int i = u; i <= d; i ++) {
                    res[index ++] = matrix[i][l];
                }
                break;
            }
            for (int j = l; j < r; j ++) {
                res[index ++] = matrix[u][j];
            }
            for (int i = u; i < d; i ++) {
                res[index ++] = matrix[i][r];
            }
            for (int j = r; j > l; j --) {
                res[index ++] = matrix[d][j];
            }
            for (int i = d; i > u; i --) {
                res[index ++] = matrix[i][l];
            }
            u ++;
            r --;
            d --;
            l ++;
        }
        return res;
    }
}
