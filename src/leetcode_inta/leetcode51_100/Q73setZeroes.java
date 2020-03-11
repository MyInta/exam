package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/19
 * @describe 给定一个 m x n 的矩阵，如果一个元素为 0，
 * 则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 */
public class Q73setZeroes {
    //使用n + m空间存储
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] flagN = new boolean[n];
        boolean[] flagM = new boolean[m];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == 0) {
                    flagN[j] = true;
                    flagM[i] = true;
                }
            }
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (flagN[j] || flagM[i]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    //O（1）空间思路，使用矩阵第一行和第一列记录0，并且记录下第一行与第一列可能原有的0，用来后续更改
    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        //分别是首列与首行存0情况
        boolean flagN = false;
        boolean flagM = false;
        for (int i = 0; i < m; i ++) {
            //判断第一列原先是否存在0
            if (matrix[i][0] == 0) flagN = true;
        }
        for (int j = 0; j < n; j ++) {
            //判断第一行原先是否存在0
            if (matrix[0][j] == 0) flagM = true;
        }
        //判断非首行首列的0情况，在首行首列进行更改
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //遍历非首行首列，以首行首列的0为标志更改
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != 0 && (matrix[i][0] == 0 || matrix[0][j] == 0)) {
                    matrix[i][j] = 0;
                }
            }
        }
        //然后解放首行首列
        if (flagN) {
            for (int i = 0; i < m; i ++) {
                matrix[i][0] = 0;
            }
        }
        if (flagM) {
            for (int j = 0; j < n; j ++) {
                matrix[0][j] = 0;
            }
        }
    }
}
