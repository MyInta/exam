package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2019/10/22
 * @describe 打乱一个没有重复元素的数组。
 *
 * 示例:
 *
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 *
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 *
 */
public class Q384Solution {

    private int[] nums;
    private int[] oldNums;
    public Q384Solution(int[] nums) {
        this.nums = nums;
        this.oldNums = nums.clone();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        nums = oldNums;
        oldNums = oldNums.clone();
        return nums;
    }

    private int getRandomNum(int start, int end) {
        return start + (int)(Math.random() * (end - start + 1));
    }
    private void swap(int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int len = nums.length;
        for (int i = 0; i < len; i ++) {
            int rand = getRandomNum(i, len - 1);
            swap(i, rand);
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */