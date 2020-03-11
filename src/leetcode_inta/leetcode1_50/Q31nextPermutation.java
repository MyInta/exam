package leetcode_inta.leetcode1_50;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/7
 * @describe 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class Q31nextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) return;
        int maxNum_index = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i --) {
            if (nums[i + 1] <= nums[i]) {
                maxNum_index = i;
            } else {
                break;
            }
        }
        if (maxNum_index == 0) {
            //如果为零，直接排列nums，即可
            Arrays.sort(nums);
        } else {
            //交换前一位和末端
            int temp = nums[maxNum_index - 1];
            int endIndex = nums.length - 1;
            while (temp >= nums[endIndex]) {
                endIndex --;
            }
            nums[maxNum_index - 1] = nums[endIndex];
            nums[endIndex] = temp;
            //并且将后面进行排序
            Arrays.sort(nums, maxNum_index, nums.length);
        }
    }
}
