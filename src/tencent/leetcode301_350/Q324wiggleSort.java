package tencent.leetcode301_350;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/30
 * @describe 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 *
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 *
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class Q324wiggleSort {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        Arrays.sort(nums);
        int[] new_num = new int[nums.length];
        int mid = (nums.length & 1) == 0 ? nums.length >> 1 : (nums.length >> 1) + 1;

        int index = (mid - 1) << 1;
        for (int i = 0; i < mid; i ++) {
            new_num[index] = nums[i];
            index -= 2;
        }
        index = ((nums.length - mid) << 1) - 1;
        for (int i = mid; i < nums.length; i ++) {
            new_num[index] = nums[i];
            index -= 2;
        }
        index = 0;
        for (int num : new_num) {
            nums[index ++] = num;
        }
    }
}
