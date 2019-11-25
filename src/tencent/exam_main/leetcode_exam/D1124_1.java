package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/11/24
 * @describe
 */
public class D1124_1 {
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length == 0) return 0;
        int res = 0;
        int x = points[0][0];
        int y = points[0][1];
        for (int i = 1; i < points.length; i ++) {
            int[] point = points[i];
            int p_x = point[0];
            int p_y = point[1];
            res += Math.max(Math.abs(p_x - x), Math.abs(p_y - y));
            x = p_x;
            y = p_y;
        }
        return res;
    }
}
