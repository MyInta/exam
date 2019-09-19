package tencent.leetcode1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/19
 * @describe 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class Q39CombinationSum {
    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        solution(candidates, target, 0, new ArrayList<>());
        return res;
    }
    private void solution(int[] nums, int target, int st, ArrayList<Integer> list) {
        //有了for循环中的判断，可以省去target小于0的情况，且不进入递归
        if (st > nums.length-1/* || target < 0*/) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = st; i < nums.length&&target - nums[i] >= 0; i++) {
            list.add(nums[i]);
            solution(nums, target-nums[i], i,list);
            list.remove(list.size()-1);
        }
    }
}
