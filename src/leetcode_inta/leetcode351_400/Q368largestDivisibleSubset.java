package leetcode_inta.leetcode351_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2021/4/23
 * @describe 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，
 * 子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数 互不相同
 */
public class Q368largestDivisibleSubset {
    private boolean[][] visited;
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        visited = new boolean[len][len];
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            System.out.println(i + "-");
            for (int j = i + 1; j < len; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                if (nums[j] % nums[i] != 0) {
                    continue;
                }
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[i]);
                temp.add(nums[j]);
                dfs(temp, j, nums);
                lists.add(temp);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (List<Integer> list : lists) {
            System.out.println(list);
            if (list.size() > res.size()) {
                res = list;
            }
        }
        if (res.size() == 0) {
            res.add(nums[0]);
        }
        return res;
    }

    private void dfs(List<Integer> temp, int start, int[] nums) {
        int index = start;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] % nums[index] == 0) {
                temp.add(nums[i]);
                index = i;
            }
        }
    }
}
