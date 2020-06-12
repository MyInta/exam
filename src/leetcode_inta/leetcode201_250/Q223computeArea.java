package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2020/6/12
 * @describe 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 *
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 *
 * Rectangle Area
 *
 * 示例:
 *
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 *
 * 说明: 假设矩形面积不会超出 int 的范围。
 *
 */
public class Q223computeArea {
    //计算两矩阵左下x_max,y_max以及右上x_min,y_min，比较是否构成左下到右上的矩阵（方向固定左下->右上），有需要删除这个重叠面积
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int x_max = Math.max(A, E);
        int y_max = Math.max(B, F);
        int x_min = Math.min(C, G);
        int y_min = Math.min(D, H);
        //比较这个矩阵是否存在
        int rep = 0;
        if (x_max < x_min && y_max < y_min) rep += (x_min - x_max) * (y_min - y_max);
        return (C - A) * (D - B) + (G - E) * (H - F) - rep;
    }
}
