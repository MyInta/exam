package leetcode_inta.leetcode801_850;

/**
 * @author inta
 * @date 2020/3/18
 * @describe 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 示例 1：
 *
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 *
 * 说明：
 *
 *     两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 *     矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 *
 */
public class Q836isRectangleOverlap {
    //三种重叠方式 左下角在区间内（不能触碰上边和右边）；右上角在区间内（不能触碰下边和左边）；
    // 第三种注意下：左下角和右上角范围均于外围扩展处（左下角限值上边和右边）（右上角限值左边和下边）
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //先把被比较对象的上右下左边全找到
        int up = rec2[2], right = rec2[3], down = rec2[0], left = rec2[1];
        //按照前面我们的三种情况进行比较
        boolean first = rec1[0] >= down && rec1[0] < up && rec1[1] >= left && rec1[1] < right;
        boolean second = rec1[2] > down && rec1[2] <= up && rec1[3] > left && rec1[3] <= right;
        boolean third = rec1[0] < up && rec1[1] < right && rec1[2] > down && rec1[3] > left;
        return first || second || third;
    }

    //官网考虑的是什么情况不重叠，当一方最大值小于另一方最小值时
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return !(rec1[0] >= rec2[2] ||
                 rec1[1] >= rec2[3] ||
                 rec1[2] <= rec2[0] ||
                 rec1[3] <= rec2[1]);
    }
}
