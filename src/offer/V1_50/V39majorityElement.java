package offer.V1_50;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/27
 * @describe 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *  
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *  
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *  LC169
 */
public class V39majorityElement {
    //解法感觉好多，比如可以统计每个元素数量，返回数量最大的；或者我这边直接排序返回中间值即可
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //比较有意思的，ON的摩尔投票法
    public int majorityElement2(int[] nums) {
        int count = 1;
        int mark = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            count += mark == nums[i] ? 1 : -1;
            if (count == 0) {
                mark = nums[i];
                count ++;
            }
        }
        return mark;
    }
}
