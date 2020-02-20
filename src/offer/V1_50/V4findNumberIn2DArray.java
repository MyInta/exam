package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/20
 * @describe 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
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
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *  
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 * 同leetcode240
 */
public class V4findNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) return false;
        int m = matrix[0].length;
        if (m == 0) return false;
        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {
            if (matrix[i][j] < target) return false;
            //二分查找
            int left = 0, right = j;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (matrix[i][mid] > target) {
                    right = mid - 1;
                } else if (matrix[i][mid] == target) {
                    return true;
                } else {
                    left = mid + 1;
                }
            }
            int up = 0, down = i;
            while (up <= down) {
                int mid = up + ((down - up) >> 1);
                if (matrix[mid][j] > target) {
                    down = mid - 1;
                } else if (matrix[mid][j] == target) {
                    return true;
                } else {
                    up = mid + 1;
                }
            }
            i --;
            j --;
        }
        return false;
    }

    //还是大佬给的思路简单，左下或者右上，考虑元素与目标大小，缩减行或列
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        //因为在240我用的是右上，这次我用左下
        int row = matrix.length - 1;
        int j = 0;
        while (row >= 0 && j < matrix[0].length) {
            if (matrix[row][j] > target) {
                //如果左下比目标大，那可能在上一行
                row --;
            } else if (matrix[row][j] < target) {
                //如果比目标小，那就可能在右边一列
                j ++;
            } else {
                return true;
            }
        }
        //一直没找到就是无
        return false;
    }
}
