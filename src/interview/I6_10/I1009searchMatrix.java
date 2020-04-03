package interview.I6_10;

/**
 * @author inta
 * @date 2020/4/3
 * @describe 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class I1009searchMatrix {
    //这道题目做过了吧，右上或者左下遍历寻找
    public boolean searchMatrix(int[][] matrix, int target) {
        //这里我们用左上做吧
        int n = matrix.length;
        if (n == 0) return false;
        int m = matrix[0].length;
        if (m == 0) return false;
        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] > target) {
                j --;
            } else if (matrix[i][j] < target) {
                i ++;
            } else {
                return true;
            }
        }
        return false;
    }
}
