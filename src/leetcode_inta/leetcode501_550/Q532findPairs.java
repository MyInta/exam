package leetcode_inta.leetcode501_550;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/2/10
 * @describe 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
 * 这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
 *
 * 示例 1:
 *
 * 输入: [3, 1, 4, 1, 5], k = 2
 * 输出: 2
 * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * 示例 2:
 *
 * 输入:[1, 2, 3, 4, 5], k = 1
 * 输出: 4
 * 解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * 示例 3:
 *
 * 输入: [1, 3, 1, 5, 4], k = 0
 * 输出: 1
 * 解释: 数组中只有一个 0-diff 数对，(1, 1)。
 * 注意:
 *
 * 数对 (i, j) 和数对 (j, i) 被算作同一数对。
 * 数组的长度不超过10,000。
 * 所有输入的整数的范围在 [-1e7, 1e7]。
 *
 */
public class Q532findPairs {
    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Set<Integer> set = new HashSet<>();
        //用来存储是否重复的值
        Set<Integer> dub = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (k == 0) {
                if (!dub.contains(num)) {
                    if (set.contains(num)) {
                        res ++;
                        dub.add(num);
                    }
                }
            } else {
                if (set.contains(num)) continue;
                if (set.contains(num - k)) res ++;
                if (set.contains(num + k)) res ++;
            }
            set.add(num);
        }
        return res;
    }

    //试试思路一样，使用数组来做
    public int findPairs2(int[] nums, int k) {
        if (k < 0) return 0;
        Arrays.sort(nums);
        int res = 0, start = 0;
        //这个变量是用来去除遍历到的重复值情况
        int pre = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i ++) {
            //如果此时i位置元素-start位置元素值超过k，说明区间大了，start得右移，或者发现pre是重复值，也右移
            if (nums[i] - nums[start] > k || pre == nums[start]) {
                start ++;
                //因为已经右移了，我们要考虑nums[i]是否可用，可是下一轮i会++，所以只要起始点不在i上，我们就回退i
                if (start != i) {
                    i --;
                }
            } else if (nums[i] - nums[start] == k) {
                //这种情况自然是符合我们解了
                res ++;
                //并且把找到匹配题意的元素记录下来
                pre = nums[start];
                //然后继续下一个start位置
                start ++;
            }
        }
        return res;
    }
}
