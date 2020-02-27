package offer.V51_100;

/**
 * @author inta
 * @date 2020/2/27
 * @describe 统计一个数字在排序数组中出现的次数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 * LC34
 */
public class V53_1search {
    //二分找左边界和右边界咯
    public int search(int[] nums, int target) {
        return findR(nums, target) - findL(nums, target);
    }
    //返回第一个大于等于target的值所在的索引位置
    private int findL(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }
    //返回第一个比target大的索引位置
    private int findR(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] <= target) {
                left = mid + 1;
            }
        }
        return left;
    }
}
