package leetcode_inta.leetcode1_50;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/7
 * @describe 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class Q47permuteUnique {
    //参考了Q46的非重复数组全排列，考虑重复值即可
    private List<List<Integer>> res;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        //因为有重复值，需要考虑，排序之后好处理些
        Arrays.sort(nums);
        res = new LinkedList<>();
        visited = new boolean[nums.length];
        solution(nums, new Stack<>(), 0, nums.length);
        return res;
    }
    private void solution(int[] nums, Stack<Integer> stack, int start, int end) {
        if (start == end) {
            res.add(new LinkedList<>(stack));
            return;
        }
        for (int i = 0; i < end; i ++) {
            //去重 从nums[1]开始 当前元素和前者相等，并且前者没有使用过，那么前者元素会在后面出现，导致重复
            //可以理解为，前者元素主导情况下已经遍历过一遍，然后你换了个后者值相等的元素主导，是导致重复的
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            stack.push(nums[i]);
            visited[i] = true;
            solution(nums, stack, start + 1, end);
            visited[i] = false;
            stack.pop();
        }
    }

    //同样，我们使用数组交换的方式来实现下，看看效率是否能提升
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        dfs(res, list, nums, 0, nums.length);
        return res;
    }
    //不便于理解
//    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int start, int end) {
//        if (start == end) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        for (int i = start; i < end; i ++) {
//            //去重，判断区间[start,i]内有无和list.get(i)重复的值，重复说明已经交换过了，该数无需再交换
//            int temp = list.get(i);
//            //用来判断是否为重复交换，默认为非重复
//            boolean flag = false;
//            for (int j = start; j < i; j ++) {
//                if (temp == list.get(j)) {
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag) continue;
//            Collections.swap(list, start, i);
//            dfs(res, list, nums, start + 1, end);
//            Collections.swap(list, start, i);
//        }
//    }
    //同上，但改为set保存交换过哪些元素，方便理解
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int start, int end) {
        if (start == end) {
            res.add(new ArrayList<>(list));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < end; i ++) {
            //去重
            if (set.contains(list.get(i))) continue;
            set.add(list.get(i));
            Collections.swap(list, start, i);
            dfs(res, list, nums, start + 1, end);
            Collections.swap(list, start, i);
        }
    }
}
