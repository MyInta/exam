package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/5
 * @describe 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class Q34searchRange {
    private int[] res = new int[2];
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        res[0] = findLeft(nums, left, right, target);
        res[1] = findRight(nums, left, right, target);
        return res;
    }
    private int findLeft(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        //考虑当所有数都小于目标值时，left靠近弹出while时为初始的right，即nums.length
        if (left == nums.length) return -1;
        //考虑目标大小在数组范围内，但没有该值时
        return nums[left] == target ? left : -1;
    }

    private int findRight(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        //考虑所有数都大于目标值时
        if (right == 0) return -1;
        //考虑目标大小在数组范围内，但没有该值时
        return nums[right - 1] == target ? right - 1 : -1;
    }
}