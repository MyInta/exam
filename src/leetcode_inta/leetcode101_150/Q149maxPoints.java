package leetcode_inta.leetcode101_150;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/11/5
 * @describe 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 *
 * 示例 1:
 *
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 *
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 *
 */
public class Q149maxPoints {
    //直接比较斜率值是不精准的，要考虑用字符串比较，而想斜率一样比较成功，要约为最简式
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) return 0;
        //记录对应最简式的字符串，出现的次数（点个数）
        Map<String, Integer> map;
        int res = 1;
        for (int i = 0; i < points.length - 1; i ++) {
            map = new HashMap<>();
            int[] p1 = points[i];
            int maxPoint = 1;
            //也要考虑同一个点可能重复很多次
            int repeat = 0;
            for (int j = i + 1; j < points.length; j ++) {
                int[] p2 = points[j];
                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];
                //同一点的情况下，数量直接加一，然后继续下一点
                if (dx == 0 && dy == 0) {
                    repeat ++;
                    continue;
                }
                //这个时候，能导致g为0的情况，dy为0而dx是不会为0的
                int g = gcd(dy, dx);
                StringBuilder a = new StringBuilder();
                if (g != 0) {
                    a.append(dy / g).append("/").append(dx / g);
                } else {
                    a.append(0).append("/").append(dx);
                }
                String a_str = a.toString();
                //default为1，是因为一条线有两个点
                map.put(a_str, map.getOrDefault(a_str, 1) + 1);
                maxPoint = Math.max(maxPoint, map.get(a_str));
            }
            //比较同一线上面点数量加上同一点重复出现的数量
            res = Math.max(res, maxPoint + repeat);
        }
        return res;
    }

    //最大公约数
    private int gcd(int y, int x) {
        if (x == 0) return y;
        return gcd(x, y % x);
    }

}
