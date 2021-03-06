package leetcode_inta.leetcode701_750;

/**
 * @author inta
 * @date 2019/12/19
 * @describe 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 示例 1:
 *
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 示例 2:
 *
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * 说明:
 *
 * nums 的长度范围为 [0, 10000]。
 * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 */
public class Q724pivotIndex {
    // 最简单的思路：求总和，再找左和为（总和减当前值）再取一半值时的索引位置
    public int pivotIndex(int[] nums) {
        //暴力
        int res = 0;
        for (int num : nums) {
            res += num;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((res - nums[i]) % 2 == 0 && count == (res - nums[i]) / 2) {
                return i;
            }
            count += nums[i];
        }
        return -1;
    }

    // 使用数组保存前缀和，查询的时候直接用数组查询，省去重复计算
    public int pivotIndex2(int[] nums) {
        int[] counts = new int[nums.length + 1]; // 预留一个位置假定索引0时候，前缀和为counts[0]=0
        for (int i = 1; i <= nums.length; i++) {
            counts[i] = counts[i - 1] + nums[i - 1];
        }
        for (int j = 0; j < nums.length; j++) {
            if (counts[j] * 2 + nums[j] == counts[nums.length]) {
                return j;
            }
        }
        return -1;
    }
}
