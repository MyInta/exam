package tencent.leetcode51_100;

/**
 * @author inta
 * @date 2020/1/5
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 */
public class Q81search {
    //分而治之的思想
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        return solution(nums, 0, nums.length - 1, target);
    }
    private boolean solution(int[] nums, int left, int right, int target) {
        if (left == right) {
            return nums[left] == target;
        }
        int mid = left + ((right - left) >> 1);
        return solution(nums, left, mid, target) || solution(nums, mid + 1, right, target);
    }

    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left ++;
                right --;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return false;
    }
}
