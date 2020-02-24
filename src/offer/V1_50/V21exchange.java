package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/22
 * @describe 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 *
 */
public class V21exchange {
    //自己的做法，考虑左边指针和右边指针，在区间内，只要符合要求就缩减范围，不符合考虑交换
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if ((nums[right] & 1) == 0) {
                right --;
                continue;
            }
            if ((nums[left] & 1) == 1) {
                left ++;
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
        return nums;
    }

    public int[] exchange2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[right] & 1) == 0) {
                right --;
            }
            while (left < right && (nums[left] & 1) == 1) {
                left ++;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
        return nums;
    }
}
