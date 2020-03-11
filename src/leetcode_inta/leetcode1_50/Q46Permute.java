package leetcode_inta.leetcode1_50;

import java.util.*;

/**
 * @author inta
 * @date 2019/9/17
 * @describe 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Q46Permute {
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        visited = new boolean[nums.length];
        solution(nums, 0, new Stack<>());
        return res;
    }
    private void solution(int[] nums, int curIndex, Stack<Integer> stack) {
        //如果当前索引已经是nums的最后一个元素，在添加完元素后直接返回
        if (curIndex == nums.length) {
            //将stack中元素都加入到数组里面
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //如果该位置元素没有被访问过，就安插进堆栈中
            if (!visited[i]) {
                stack.push(nums[i]);
                //标记为已经访问过的
                visited[i] = true;
                solution(nums, curIndex+1, stack);
                //重要的复原
                visited[i] = false;
                //弹栈
                stack.pop();
            }
        }
    }

    //使用官方的回溯法
    private class Q46Permute2{
        private List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            if (nums.length == 0) return res;
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num:nums) {
                list.add(num);
            }
            solution(list, 0, nums.length);
            return res;
        }
        private void solution(List<Integer> list, int begin, int len) {
            //如果开始索引已经全都遍历了
            if (begin == len) {
                //拷贝一份数组值保存
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = begin; i < len; i++) {
                Collections.swap(list,begin,i);
                solution(list, begin+1, len);
                Collections.swap(list,begin,i);
            }
        }
    }
}
