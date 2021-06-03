package leetcode_inta.leetcode501_550;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/11/15
 * @describe 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 * 示例 1:
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 注意: 给定的二进制数组的长度不会超过100000。
 */
public class Q525findMaxLength {
    // 使用暴力破解的方法 时间超了
    public int findMaxLength(int[] nums) {
        int size = nums.length;
        int[] counts = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            counts[i] = counts[i - 1] + (nums[i - 1] & 1);
        }
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 2; j <= size; j += 2) {
                if (counts[j] - counts[i] == (j - i) / 2) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    // 使用map来保存之前累加出现过的记录 核心思路：0改成-1后，一个累加值出现之后，第二次以及第N次出现以前，必定01数量相等
    public int findMaxLength2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(curSum)) {
                res = Math.max(res, i - map.get(curSum));
            } else {
                map.put(curSum, i);
            }
        }
        return res;
    }
}
