package tencent.leetcode1_50;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/30
 * @describe 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class Q40combinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        //先把数组排序，在遍历的时候注意不要让起始元素重复
        Arrays.sort(candidates);
        dfs(candidates, 0, candidates.length, target, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] cand, int start, int end, int target, ArrayList<Integer> list, List<List<Integer>> res) {
        if (start >= end || target < cand[start]) {
            return;
        }
        if (target == cand[start]) {
            list.add(target);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            while (start < end - 1 && cand[start + 1] == cand[start]) {
                start ++;
            }
            dfs(cand, start + 1, end, target, list, res);
        } else if (target > cand[start]) {
            list.add(cand[start]);
            dfs(cand, start + 1, end, target - cand[start], list, res);
            list.remove(list.size() - 1);
            while (start < end - 1 && cand[start + 1] == cand[start]) {
                start ++;
            }
            dfs(cand, start + 1, end, target, list, res);
        }
    }
}
