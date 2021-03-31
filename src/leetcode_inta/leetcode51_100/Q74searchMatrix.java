package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/29
 * @describe 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
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
    // 两次二分，一次从矩阵[i][0]，一次从矩阵[0][j]入手
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 考虑比首位小,比末位大的情况
        if (matrix[0][0] > target || matrix[m - 1][n - 1] < target) {
            return false;
        }
        int low = 0;
        int higth = m;
        while (low < higth) {
            int mid = low + (higth - low) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                higth = mid;
            } else {
                low = mid + 1;
            }
        }
        // 此时找到的low是第一个大于target的行坐标，需要-1修正到所在行
        low--;

        int left = 0;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (matrix[low][mid] == target) {
                return true;
            } else if (matrix[low][mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
