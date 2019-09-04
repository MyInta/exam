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

    /**
     * 动态规划解法
     */
    private class Q300LengthOfLIS2 {
        public int lengthOfLIS(int[] nums) {
            int nums_length = nums.length;
            if (nums_length == 0) return 0;
            //创建一个代表每个位置比较大小所能得到最长序列数量的数组
            int[] dp = new int[nums_length];
            //可能部分元素为0，例如[2,2] 不存在大小差，导致dp都默认为0，所以要填充下
            Arrays.fill(dp,1);
            //从该序列第二个位置开始计算，第一个位置铁定为1，除非空返回0
            for (int i = 1; i < nums_length; i++) {
                //进行挨个与前面元素比较,查找当前段可能的升序组合中的最大数值
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int res = 0;
            //先全遍历dp得到最大值
            for (int temp: dp) {
                //因为已经遍历所有元素，也得到了dp
                res = Math.max(res, temp);
            }
            return res;
        }
    }

    /**
     * 动态规划加二分法 时间复杂度可减少到nlogn
     */
    private class Q300LengthOfLIS3 {
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            //特解
            if (len <= 1) return len;
            //用以记录每个元素遍历之后新排序的数组
            int[] notes = new int[len];
            notes[0] = nums[0];
            //用一个变量记录notes中最后一位已经被赋值的元素索引
            int end = 0;
            for (int i = 1;i < len; i++) {
                if (nums[i] > notes[end]) {
                    //将比较大的元素加入到后面,并且记录的end值会发生改变
                    notes[++end] = nums[i];
                } else {
                    //如果遍历的元素比该位置notes小，那么去寻找到大于该元素但最小的地方进行替换(二分)
                    int left = 0;
                    int right = end;
                    while (left < right) {
                        int mid = left + ((right - left) >>>1);
                        if (notes[mid] < nums[i]) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    //替换操作，而记录的end不会发生改变
                    notes[left] = nums[i];
                }
            }
            //因为我们的end只是个索引，题意需要长度
            return end+1;
        }
    }
}
