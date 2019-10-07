package tencent.leetcode551_600;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/7
 * @describe 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 */
public class Q581findUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = -1;
        int[] newNums = new int[nums.length];
        int start = 0;
        for (int i : nums) {
            newNums[start ++] = i;
        }
        Arrays.sort(newNums);
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != newNums[i]) {
                left = i;
                break;
            }
        }
        for (int j = nums.length - 1; j >= 0; j --) {
            if (nums[j] != newNums[j]) {
                right = j;
                break;
            }
        }
        return right - left + 1;
    }
}
