package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/1
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 */
public class Q33search {
    public int search(int[] nums, int target) {
        return solution(nums, 0, nums.length - 1, target);
    }

    private int solution(int[] nums, int left, int right, int target) {
        if (left == right && nums[left] == target) {
            return left;
        }
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            int l = solution(nums, left, mid, target);
            int r = solution(nums, mid + 1, right, target);
            if (l != -1) {
                return l;
            }
            if (r != -1) {
                return r;
            }
            return -1;
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        return solution(nums, 0, nums.length - 1, target);
    }
    private int solution2(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = ((right - left) >> 1) + left;
        return Math.max(solution(nums, left, mid, target), solution(nums, mid + 1, right, target));
    }
}
