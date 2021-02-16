package leetcode_inta.leetcode551_600;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/12/19
 * @describe 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * 示例 1:
 * 输入: [1,4,3,2]
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 * 提示:
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 */
public class Q561arrayPairSum {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    // 试试计数排序
    public int arrayPairSum2(int[] nums) {
        int res = 0;
        int[] counts = new int[20001];
        for (int num : nums) {
            counts[num + 10000]++;
        }
        int cur = 0;
        // 此时得到了nums的所有元素信息
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                if (cur % 2 == 0) {
                    res += i - 10000;
                }
                cur++;
                counts[i]--;
            }
        }
        return res;
    }
}
