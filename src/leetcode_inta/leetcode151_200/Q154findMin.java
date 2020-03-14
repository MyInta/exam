package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2020/1/20
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 *
 * 示例 1：
 *
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 *
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 *
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 *
 */
public class Q154findMin {
    //如果中间值比右边值大，旋转点必定在[mid + 1, right] 如果小于等于右边值，在[left,mid]
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                //如果重复，就缩减右边界，最小值是不会受影响被删光的
                right --;
            }
        }
        return nums[left];
    }
}
