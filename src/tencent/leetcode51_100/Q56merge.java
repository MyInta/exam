package tencent.leetcode51_100;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/8
 * @describe 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Q56merge {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(intervals[0][0]);
        stack.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i ++) {
            if (intervals[i][0] > stack.peek()) {
                int second = stack.pop();
                int first = stack.pop();
                int[] newInt = {first, second};
                list.add(newInt);
                stack.add(intervals[i][0]);
                stack.add(intervals[i][1]);
            } else if (intervals[i][0] <= stack.peek()) {
                int large = stack.peek();
                if (intervals[i][1] > large) {
                    stack.pop();
                    stack.add(intervals[i][1]);
                }
            }
        }

        int second = stack.pop();
        int first = stack.pop();
        int[] newInt = {first, second};
        list.add(newInt);

        int[][] res = new int[list.size()][2];
        int start = 0;
        for (int[] eve : list) {
            res[start ++] = eve;
        }
        return res;
    }
}
