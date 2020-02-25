package offer.V51_100;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/25
 * @describe 一个长度为n-1的递增排序数组中的所有数字都是唯一的，
 * 并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 */
public class V53missingNumber {
    //方法一，排序找漏洞
    public int missingNumber(int[] nums) {
        //考虑边界条件
        if (nums[0] != 0) return 0;
        int len = nums.length;
        if (nums[len - 1] != len) return len;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] - nums[i - 1] != 1) return nums[i] - 1;
        }
        return -1;
    }

    //方法二，数学计算差值
    public int missingNumber2(int[] nums) {
        int len = nums.length;
        int sum = (len + 1) * len / 2;
        int counts = 0;
        for (int num : nums) {
            counts += num;
        }
        return sum - counts;
    }

    //适合算法的二分,找第一个与本身位置不符合的元素-1值
    public int missingNumber3(int[] nums) {
        int len = nums.length;
        int left = 0, right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) {
                right = mid;
            } else if (nums[mid] == mid) {
                left = mid + 1;
            }
        }
        if (left == len) return len;
        return nums[left] - 1;
    }
}
