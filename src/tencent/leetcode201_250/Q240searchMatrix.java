package tencent.leetcode201_250;

/**
 * @author inta
 * @date 2019/10/7
 * @describe 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
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
 */
public class Q240searchMatrix {
    //思路，分为左，上，右，下，依次二分查找，并缩短范围，直到为单一元素
    public boolean searchMatrix(int[][] matrix, int target) {
        int m_low = 0;
        int m_high = matrix.length - 1;
        //说明二维矩阵无值
        if (m_high < 0) return false;
        int n_low = 0;
        int n_high = matrix[0].length - 1;

        while (m_low < m_high && n_low < n_high) {

            //左
            int L = m_low;
            int R = m_high;
            while (L <= R) {
                int mid = ((R - L) >> 1) + L;
                if (matrix[mid][n_low] == target) {
                    return true;
                } else if (matrix[mid][n_low] > target) {
                    R = mid - 1;
                } else if (matrix[mid][n_low] < target) {
                    L = mid + 1;
                }
            }
            m_high = R;

            //上
            L = n_low;
            R = n_high;
            while (L <= R) {
                int mid = ((R - L) >> 1) + L;
                if (matrix[m_low][mid] == target) {
                    return true;
                } else if (matrix[m_low][mid] > target) {
                    R = mid - 1;
                } else if (matrix[m_low][mid] < target) {
                    L = mid + 1;
                }
            }
            n_high = R;

            //右
            L = m_low;
            R = m_high;
            while (L <= R) {
                int mid = ((R - L) >> 1) + L;
                if (matrix[mid][n_high] == target) {
                    return true;
                } else if (matrix[mid][n_high] > target) {
                    R = mid - 1;
                } else if (matrix[mid][n_high] < target) {
                    L = mid + 1;
                }
            }
            m_low = L;

            //下
            L = n_low;
            R = n_high;
            while (L <= R) {
                int mid = ((R - L) >> 1) + L;
                if (matrix[m_high][mid] == target) {
                    return true;
                } else if (matrix[m_high][mid] > target) {
                    R = mid - 1;
                } else if (matrix[m_high][mid] < target) {
                    L = mid + 1;
                }
            }
            n_low = L;
        }

        //如果为单行或单列的时候，挨个查找即可
        if (m_low == m_high || n_low == n_high) {
            for (int i = m_low; i <= m_high; i ++) {
                for (int j = n_low; j <= n_high; j ++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }


    //思路，选择右上角或左下角，如右上角，数值比目标值大，那么目标可能在其左边，减少列找寻，反之，则可能在下，加行
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            //右上角元素大于目标值，减少列
            if (matrix[row][col] > target) {
                col --;
            } else if (matrix[row][col] < target) {
                row ++;
            } else if (matrix[row][col] == target) {
                return true;
            }
        }
        return false;
    }

}
