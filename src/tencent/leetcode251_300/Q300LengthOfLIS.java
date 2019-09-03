package tencent.leetcode251_300;

import java.util.*;

/**
 * @author inta
 * @date 2019/9/3
 * @describe 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 *
 */
public class Q300LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int nums_length = nums.length;
/*        Set<Integer> set = new TreeSet<>();
        for (int i = 0;i < nums_length; i++) {
            set.add(nums[i]);
        }
        int[] sortedNums = new int[set.size()];
        int sortedNums_index = 0;
        for (Integer i : set) {
            sortedNums[sortedNums_index++] = i;
        }*/
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums_length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
            }
        }
        int[] sortedNums = new int[list.size()];
        int sortedNums_index = 0;
        for (Integer i : list) {
            sortedNums[sortedNums_index++] = i;
        }
        Arrays.sort(sortedNums);
        int sortedNums_length = sortedNums.length;
        int[][] res = new int[nums_length + 1][sortedNums_length + 1];
        for (int i = 1; i <= nums_length; i++) {
            for (int j = 1; j <= sortedNums_length; j++) {
                if (nums[i - 1] == sortedNums[j - 1]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        return res[nums.length][sortedNums_length];
    }

    public static void main(String[] args) {
        Q300LengthOfLIS q = new Q300LengthOfLIS();
        int[] nums = {9,5,6,2,5,7,8};
        q.lengthOfLIS(nums);
    }

    private class Q300LengthOfLIS2 {
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            if (len == 0) return 0;
            int[][] dp = new int[len + 1][len + 1];
            for (int i = 1; i <= len; i++) {
                for (int j = i; j <= len; j++) {
                    if (nums[i - 1] < nums[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[len][len] == 0?1:dp[len][len];
        }
    }
}
