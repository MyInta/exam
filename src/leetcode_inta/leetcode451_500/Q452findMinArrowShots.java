package leetcode_inta.leetcode451_500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author inta
 * @date 2020/6/4
 * @describe 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，
 * 气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，
 * 因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。
 *
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，
 * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，
 * 则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * Example:
 *
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 */
public class Q452findMinArrowShots {
    //网友给出了，解决该问题的本质，贪心算法，按右段排序后，挨个射爆，判断是否需要新箭
    public int findMinArrowShots(int[][] points) {
        int res = 0;
        //（2020.11.23）后来增加了用例[[-2147483646,-2147483645],[2147483646,2147483647]]，防止溢出，使用Integer.compare方法
        //Arrays.sort(points, (a,b)->Integer.compare(a[1], b[1]));
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //此时数组是按照右段数值从小到达排序的
        long max = Long.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            //如果下一个气球的最左是在当前统计右段界限外，就说明需要一支新箭
            if (points[i][0] > max) {
                res ++;
                //注意这里，我们射爆这个气球的同时，右段界限被更新
                max = points[i][1];
            }
        }
        return res;
    }
}
