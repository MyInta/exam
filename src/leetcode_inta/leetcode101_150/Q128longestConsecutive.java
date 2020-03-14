package leetcode_inta.leetcode101_150;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2019/10/14
 * @describe 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class Q128longestConsecutive {
    //时间复杂度是nlogn
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int len = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            if (nums[i + 1] - nums[i] == 1) {
                right ++;
            } else if (nums[i + 1] - nums[i] == 0) {
                left ++;
                right ++;
            } else {
                len = Math.max(len, right - left + 1);
                left = i + 1;
                right = i + 1;
            }
        }
        return Math.max(len, right - left + 1);
    }

    //使用hashset给暴力解法去重后得到最优解
    public int longestConsecutive2(int[] nums) {
        int res = 0;
        Set<Integer> hs = new HashSet<>();
        for (int num : nums) hs.add(num);
        for (int num : hs) {
            if (!hs.contains(num - 1)) {
                int curNum = num;
                while (hs.contains(curNum)) {
                    curNum ++;
                }
                res = Math.max(res, curNum - num);
            }
        }
        return res;
    }
}
