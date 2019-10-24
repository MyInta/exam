package tencent.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/24
 * @describe 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 */
public class Q162findPeakElement {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            if (i < nums.length - 1 && nums[i + 1] < nums[i]) return i;
        }
        return nums.length - 1;
    }
    //符合题意得二分查找，左右逼近
    public int findPeakElement2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            //中间值大于右边值说明，峰值可能在左侧
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

}
