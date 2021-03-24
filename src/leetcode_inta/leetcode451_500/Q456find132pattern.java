package leetcode_inta.leetcode451_500;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2021/3/24
 * @describe 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * 注意：n 的值小于15000。
 * 示例1:
 * 输入: [1, 2, 3, 4]
 * 输出: False
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 * 输入: [3, 1, 4, 2]
 * 输出: True
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * 示例 3:
 * 输入: [-1, 3, 2, 0]
 * 输出: True
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 */
public class Q456find132pattern {
    // 288ms ohhhhhh
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int cur = nums[0];
        if (containDesc(getAsc(nums, cur, 1))) {
            return true;
        }
        for (int i = 1; i < nums.length - 2; i++) {
            if (nums[i] < cur) {
                cur = nums[i];
                if (containDesc(getAsc(nums, cur, i + 1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Integer> getAsc(int[] nums, int target, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = start; i < nums.length; i++) {
            if (nums[i] > target) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    private boolean containDesc(List<Integer> list) {
        if (list.size() < 2) {
            return false;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            if (min == nums[i]) {
                continue;
            }
            for (int j = nums.length - 1; j > i; j--) {
                if (min < nums[j] && nums[j] < nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 倒序找，用栈保存当前遍历值，依条件出栈
    public boolean find132pattern3(int[] nums) {
        Stack<Integer> targetThree = new Stack<>();
        int targetTwo = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < targetTwo) {
                return true;
            }
            while (!targetThree.isEmpty() && targetThree.peek() < nums[i]) {
                targetTwo = targetThree.pop();
            }
            targetThree.push(nums[i]);
        }
        return false;
    }
}
