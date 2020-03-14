package leetcode_inta.leetcode401_450;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/11/16
 * @describe 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 * 示例 1:
 *
 * 输入: [3, 2, 1]
 *
 * 输出: 1
 *
 * 解释: 第三大的数是 1.
 * 示例 2:
 *
 * 输入: [1, 2]
 *
 * 输出: 2
 *
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 *
 * 输入: [2, 2, 3, 1]
 *
 * 输出: 1
 *
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 */
public class Q414thirdMax {
    //因为用了sort，不推荐
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int first = nums[n - 1], second = nums[0], third = nums[0];
        for (int i = n - 2; i >= 0; i --) {
            if (nums[i] == first) {
                continue;
            } else if (nums[i] > second) {
                second = nums[i];
            } else if (nums[i] == second) {
                continue;
            } else if (nums[i] >= third) {
                return nums[i];
            }
        }
        return first;
    }

    //使用long范围记录
    public int thirdMax2(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                //注意先移位，再赋值
                third = second;
                second = first;
                first = num;
            } else if (num < first && num > second) {
                third = second;
                second = num;
            } else if (num < second && num > third) {
                third = num;
            }
        }
        //遍历一圈之后，判断first有没有改变，且改变为非second的数
        return (int) (third == Long.MIN_VALUE || third == second ? first : third);
    }
}
