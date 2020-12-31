package leetcode_inta.leetcode451_500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2020/11/2
 * @describe 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * 说明:
 *
 *     给定数组的长度不会超过15。
 *     数组中的整数范围是 [-100,100]。
 *     给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class Q491findSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), 0, nums);
        return res;
    }
    private void backtracking(List<List<Integer>> res, List<Integer> paths, int index, int[] nums) {
        if (paths.size() >= 2) res.add(new ArrayList<>(paths));
        //考虑到元素重复，用一个set来进行校验
        Set<Integer> check_Set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            //如果是访问过的元素值，直接跳过
            if (check_Set.contains(nums[i])) continue;
            check_Set.add(nums[i]);
            //当路径上保存的元素数量为零或者目前遍历到元素比路径最后一个元素大时，进行添加
            if (paths.isEmpty() || nums[i] >= paths.get(paths.size() - 1)) {
                paths.add(nums[i]);
                //并且进行进一步的回溯
                backtracking(res, paths, i + 1, nums);
                //递归结束后，需要把这个元素删除，方便下一步遍历
                paths.remove(paths.size() - 1);
            }
        }
    }
}
