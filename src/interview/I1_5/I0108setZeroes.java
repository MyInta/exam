package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/10
 * @describe 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2：
 *
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 */
public class I0108setZeroes {
    //简单思路就是遍历一遍，将所有需要修改的行和列信息保存，第二遍直接修改即可
    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        int n = matrix.length;
        if (n == 0) return;
        int m = matrix[0].length;
        if (m == 0) return;
        int[] countRow = new int[n];
        int[] countCol = new int[m];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (matrix[i][j] == 0) {
                    countRow[i] ++;
                    countCol[j] ++;
                }
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (countRow[i] > 0 || countCol[j] > 0) matrix[i][j] = 0;
            }
        }
    }
}
