package tencent.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/29
 * @describe 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 */
public class Q74searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        if (target > matrix[n - 1][m - 1] || target < matrix[0][0]) return false;
        int up = 0;
        int down = n;
        while (up < down) {
            int mid = up + ((down - up) >> 1);
            if (matrix[mid][m - 1] < target) {
                up = mid + 1;
            } else if (matrix[mid][m - 1] == target) {
                return true;
            } else {
                down = mid;
            }
        }
        //这时候，范围已经缩小到了在up这一行上
        int left = 0;
        int right = m;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (matrix[up][mid] < target) {
                left = mid + 1;
            } else if (matrix[up][mid] == target) {
                return true;
            } else {
                right = mid;
            }
        }
        return false;
    }
}
