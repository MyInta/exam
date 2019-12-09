package tencent.exam_main.leetcode_exam;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/12/8
 * @describe
 */
public class D1208_3 {
    //先暴力求解试一下,时间越界
    public int smallestDivisor(int[] nums, int threshold) {
        //找到数组中的最大值
        int max = 0;
        for (int num : nums) {
            if (num > max) max = num;
        }
        //起始值为1，即最小值
        int left = 1;
        if (threshold == nums.length) return max;
        //所求值在区间[left,max)上
        while (left < max) {
            int mid = left + ((max - left) >> 1);
            if (lorr(nums, mid, threshold)) {
                //当mid满足要求，说明mid可能取大了，要求最靠近的最小值
                max = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    //判断被除数key是否满足小于等于target的要求
    private boolean lorr(int[] nums, int key, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num / key;
            if (num % key != 0) sum ++;
        }
        return sum <= target;
    }
}
