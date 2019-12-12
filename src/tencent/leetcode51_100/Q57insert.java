package tencent.leetcode51_100;

import java.util.ArrayList;

/**
 * @author inta
 * @date 2019/12/12
 * @describe 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * 示例 2:
 *
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 */
public class Q57insert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            int[][] null_intervals = {newInterval};
            return null_intervals;
        }
        int n_l = newInterval[0];
        int n_r = newInterval[1];
        int len = intervals.length;
        int left = 0;
        int right = len - 1;
        for (int i = 0; i < len; i ++) {
            if (intervals[i][0] < n_l) {
                //找到插入区间左值最靠近的原数组索引
                left = Math.max(left, i);
            }
            if (intervals[i][1] > n_r) {
                //找到插入区间右值最靠近的原数组索引
                right = Math.min(right, i);
            }
        }

        //添加首部
        for (int i = 0; i < left; i ++) {
            res.add(intervals[i]);
        }
        //开始比较这两个索引对应数组左右值是否包裹插入数组元素左右值
        int[] left_nums = intervals[left];
        int[] right_nums = intervals[right];
        //只有四种大情况，不包裹,全包裹,以及部分包裹（左包裹或右包裹）
        if (left_nums[1] < n_l && right_nums[0] > n_r) {
            res.add(left_nums);
            res.add(newInterval);
            res.add(right_nums);
        } else if (n_l > right_nums[1]) {
            res.add(left_nums);
            res.add(newInterval);
        } else if (n_r < left_nums[0]) {
            res.add(newInterval);
            res.add(right_nums);
        } else if (left_nums[1] >= n_l && right_nums[0] <= n_r) {
            int[] new_temp = {Math.min(left_nums[0], n_l), Math.max(right_nums[1], n_r)};
            res.add(new_temp);
        } else if (left_nums[1] >= n_l &&  right_nums[0] > n_r) {
            int[] new_temp = {Math.min(left_nums[0], n_l), n_r};
            res.add(new_temp);
            res.add(right_nums);
        } else if (left_nums[1] < n_l && right_nums[0] <= n_r) {
            int[] new_temp = {n_l, Math.max(right_nums[1], n_r)};
            res.add(left_nums);
            res.add(new_temp);
        }

        //添加尾部
        for (int i = right + 1; i < len; i ++) {
            res.add(intervals[i]);
        }
        int size = res.size();
        int[][] new_int = new int[size][2];
        int index = 0;
        for (int[] r : res) {
            new_int[index ++] = r;
        }
        return new_int;
    }
}
