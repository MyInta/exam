package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/6
 * @describe 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class Q84largestRectangleArea {
    //超出内存上限
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int[][] dp = new int[len][len];
        dp[0][0] = heights[0];
        for (int i = 0; i < len; i ++) {
            if (i >= 1) {
                dp[i][i] = Math.max(dp[i - 1][len - 1], heights[i]);
            }
            int minHeight = heights[i];
            for (int j = i + 1; j < len; j ++) {
                minHeight = Math.min(minHeight, heights[j]);
                dp[i][j] = Math.max(heights[j], Math.max(dp[i][j - 1], minHeight * (j - i + 1)));
            }
        }
        return dp[len - 1][len - 1];
    }

    //运行速度极慢
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        int res = 0;
        dp[0] = heights[0];
        for (int i = 0; i < len; i ++) {
            res = Math.max(res, dp[len - 1]);
            int minHeight = heights[i];
            for (int j = i + 1; j < len; j ++) {
                minHeight = Math.min(minHeight, heights[j]);
                dp[j] = Math.max(heights[j], Math.max(dp[j - 1], minHeight * (j - i + 1)));
            }
        }
        return res;
    }

    public int largestRectangleArea3(int[] heights) {
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
