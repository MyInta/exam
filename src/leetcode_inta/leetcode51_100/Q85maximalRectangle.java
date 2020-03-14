package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/12
 * @describe 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 */
public class Q85maximalRectangle {

    //全局变量，记录面积
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];

        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            res = Math.max(res, findArea(heights));
        }
        return res;
    }

    private int findArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int[] left = new int[len];
        int[] right = new int[len];
        int res = 0;
        left[0] = -1;
        right[len - 1] = len;
        for (int i = 1; i < len; i ++) {
            int tempLeft = i - 1;
            while (tempLeft >= 0 && heights[tempLeft] >= heights[i]) {
                tempLeft --;
            }
            left[i] = tempLeft;
        }
        for (int i = len - 1; i >= 0; i --) {
            int tempRight = i + 1;
            while (tempRight < len && heights[tempRight] >= heights[i]) {
                tempRight ++;
            }
            right[i] = tempRight;
        }
        for (int i = 0; i < len; i ++) {
            res = Math.max(res, heights[i] * (right[i] - left[i] - 1));
        }
        return res;
    }

}
