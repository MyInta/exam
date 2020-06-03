package leetcode_inta.leetcode401_450;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author inta
 * @date 2020/6/3
 * @describe 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 *     可以认为区间的终点总是大于它的起点。
 *     区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class Q435eraseOverlapIntervals {
    //核心思想：贪心找结尾最小的区段最多数量，总数减去最多不重复区域数量即为解
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //此时intervals内优先结尾最小字段在前面
        int n = intervals.length;
        //count记录不重复字段数量，mark标记当前不重复字段最右结尾数值
        int count = 0, mark = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            //遍历每一个字段，只要新的字段开头是在之前结尾之后，就说明可以构成非重叠，添加并更新结尾
            if (interval[0] >= mark) {
                count ++;
                mark = interval[1];
            }
        }
        return n - count;
    }
}
