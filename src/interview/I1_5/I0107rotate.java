package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/9
 * @describe 给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。
 *
 * 不占用额外内存空间能否做到？
 *
 *  
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * 示例 2:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 */
public class I0107rotate {
    //列决定了行，而行决定了列，注意这里只有行转为列需要留心，0->n-1 1->n-2 n-1->0 即列 为n-行
    //为了防止值重叠，我们换成两部分，先按两角对称轴对称，再按中间列对称
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        //先按两角对称轴对称
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //再按照中心列对称
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n / 2; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
