package tencent.leetcode601_650;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/1/18
 * @describe 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 */
public class Q628maximumProduct {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    //记录最小的两个和最大的三个，取两者之一(负负正和正正正)
    public int maximumProduct2(int[] nums) {
        //min1最小，min2第二小
        int min1 = Integer.MAX_VALUE, min2 = min1;
        //max1最大,max3第三大
        int max1 = Integer.MIN_VALUE, max2 = max1, max3 = max1;
        for (int i = 0; i < nums.length; i ++) {
            //先考虑最大值的保留情况
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (nums[i] > max3) {
                max3 = nums[i];
            }
            //再考虑最小值的保留情况
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
