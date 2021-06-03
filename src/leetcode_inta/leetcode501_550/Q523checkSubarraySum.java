package leetcode_inta.leetcode501_550;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/6/1
 * @describe 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，
 * 其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 * 示例 1:
 * 输入: [23,2,4,6,7], k = 6
 * 输出: True
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2:
 * 输入: [23,2,6,4,7], k = 6
 * 输出: True
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * 说明:
 *
 *     数组的长度不会超过10,000。
 *     你可以认为所有数字总和在 32 位有符号整数范围内。
 */
public class Q523checkSubarraySum {
    // 暴力算法，看一下是否可行
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            for (int j = i + 1; j < n; j++) {
                start += nums[j];
                if ((start == 0 && k == 0) || (k != 0 &&  start % k == 0)) return true;
            }
        }
        return false;
    }

    // 官解使用map来保存累加的num值，利用map查询累加值前一个差k倍数的前缀值索引位置，通过map的v保存的索引之差来确定间距是否大于等于2
    public boolean checkSubarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 先预先放一个0进去，防治空指针(其索引是-1)
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            // 只要map中存在sum，就说明有一个位置是与当前位置累加值相差k的n倍(0~n)
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    // 当两者距离超过1，符合题意
                    return true;
                }
            } else {
                // 如果没有找到，那么我们要把新的累加值情况放到map中
                map.put(sum, i);
            }
        }
        return false;
    }
}
