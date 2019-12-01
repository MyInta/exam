package tencent.leetcode201_250;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2019/12/1
 * @describe 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 */
public class Q219containsNearbyDuplicate {
    //先用最简单的思路与来做，map保存元素和索引，然后遍历，找距离最大值
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        if (k <= 0) return false;
        for (int i = 0; i < nums.length; i ++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[i], i);
//            } else {
//                if (i - map.get(nums[i]) <= k) return true;
//                map.put(nums[i], i);
//            }
            //优化一下，让判断改下顺序,提高了一毫秒。。。效率就多打败了百分之十的人
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }

    //使用set来做,和上面的效率没有多少变化，为什么？
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (k <= 0) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i ++) {
            if (set.contains(nums[i])) return true;
            //如果不包含，那么就是新的元素，查看加入之后的长度是否大于k,需要修改长度
            set.add(nums[i]);
            //将前第k个元素从set中去除，因为set没有索引一说,保持set的长度在包含k长度内
            if (set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }
}
