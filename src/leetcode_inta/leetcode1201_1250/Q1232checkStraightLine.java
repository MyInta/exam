package leetcode_inta.leetcode1201_1250;

/**
 * @author inta
 * @date 2021/1/17
 * @describe 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
 * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * 示例 1：
 *
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *
 * 提示：
 *
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 */
public class Q1232checkStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int[] origin = coordinates[0];
        int difY = coordinates[1][1] - origin[1];
        int difX =  coordinates[1][0] - origin[0];
        if (difY == 0) {
            return checkAxis(coordinates, origin, 1);
        }
        if (difX == 0) {
            return checkAxis(coordinates, origin, 0);
        }
        double k = 1.0 * difY / difX;
        for (int i = 2; i < coordinates.length; i++) {
            double curK = 1.0 * (coordinates[i][1] - origin[1]) / (coordinates[i][0] - origin[0]);
            if (Double.compare(k, curK) != 0) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkAxis(int[][] coordinates, int[] origin, int axis) {
        for (int[] coordeinate : coordinates) {
            if (coordeinate[axis] != origin[axis]) {
                return false;
            }
        }
        return true;
    }

    // 上面思路简单，但是其实搞复杂了，运用数学的方式，判断两者斜率是否相等可以用x1*y2与x2*y1进行判断
    public boolean checkStraightLine2(int[][] coordinates) {
        for (int i = 1; i < coordinates.length - 1; i++) {
            // x1*y2与y1*x2进行判断
            int left = (coordinates[i - 1][0] - coordinates[i][0]) * (coordinates[i][1] - coordinates[i + 1][1]);
            int right = (coordinates[i - 1][1] - coordinates[i][1]) * (coordinates[i][0] - coordinates[i + 1][0]);
            if (left != right) {
                return false;
            }
        }
        return true;
    }
}
