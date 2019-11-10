package tencent.exam_main.leetcodeD162;

/**
 * @author inta
 * @date 2019/11/10
 * @describe
 */
public class Q5255 {
    public int oddCells(int n, int m, int[][] indices) {
        int[][] nums = new int[n][m];
        for (int[] indice : indices) {
            for (int i = 0; i < m; i ++) {
                nums[indice[0]][i] ++;
            }
            for (int j = 0; j < n; j ++) {
                nums[j][indice[1]] ++;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if ((nums[i][j] & 1) == 1) {
                    res ++;
                }
            }
        }
        return res;
    }

}
