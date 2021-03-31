package leetcode_inta.leetcode51_100;

import java.util.*;

/**
 * @author inta
 * @date 2019/9/18
 * @describe 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class Q90SubsetsWithDup {
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new ArrayList<>();
        visited = new boolean[nums.length];
        // 先将其排序，可以比较前后两个数是否一样而且是否不该取值
        Arrays.sort(nums);
        robot(0, nums);
        return res;
    }

    private void robot(int st, int[] nums) {
        if (st > nums.length-1) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    list.add(nums[i]);
                }
            }
            res.add(list);
            return;
        }
        if (st > 0&& nums[st - 1] == nums[st] && !visited[st - 1]) {
            visited[st] = false;
            robot(st + 1, nums);
        } else {
            visited[st] = true;
            robot(st + 1, nums);
            visited[st] = false;
            robot(st + 1, nums);
        }
    }

    private class Q90SubsetsWithDup2 {
        private List<List<Integer>> res;
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            res = new ArrayList<>();
            Arrays.sort(nums);
            solution(0, new ArrayList<>(), nums);
            return res;
        }
        private void solution(int st, ArrayList<Integer> list, int[] nums) {
            res.add(new ArrayList<>(list));
            for (int i = st; i < nums.length; i++) {
                if (i > st && nums[i -1] == nums[i]) {
                    continue;
                }
                list.add(nums[i]);
                solution(i + 1, list, nums);
                list.remove(list.size()-1);
            }
        }
    }

    class Solution {
        private Set<List<Integer>> set;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            this.set = new HashSet<>();
            Arrays.sort(nums);
            solution(new ArrayList<>(), nums, 0);
            List<List<Integer>> result = new ArrayList<>(set);
            return result;
        }

        private void solution(List<Integer> list, int[] nums, int start) {
            if (start == nums.length) {
                set.add(new ArrayList<>(list));
                return;
            }
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                solution(list, nums, i + 1);
                list.remove(list.size() - 1);
            }
            set.add(new ArrayList<>(list));
        }
    }
}
