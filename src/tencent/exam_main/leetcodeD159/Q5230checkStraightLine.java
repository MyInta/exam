package tencent.exam_main.leetcodeD159;

/**
 * @author inta
 * @date 2019/10/20
 * @describe 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
 * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，
 * 是则返回
 * <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace">true</font>，
 * 否则请返回
 * <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace">false</font>
 */
public class Q5230checkStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int[] first = coordinates[0];
        int x = first[0];
        int y = first[1];
        int[] second = coordinates[1];
        double a = (double)(second[1] - first[1]) / (second[0] - first[0]);
        for (int i = 2; i < coordinates.length; i ++) {
            int[] temp = coordinates[i];
            if ((double)(temp[1] - y) / (temp[0] - x) != a) {
                return false;
            }
        }
        return true;
    }
}
