package tencent.leetcode201_250;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/26
 * @describe 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 
 * 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 *
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 */
public class Q220containsNearbyAlmostDuplicate {
    //首先想到暴力法,效率极低是肯定的
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j ++) {
                if (Math.abs((long)nums[j] - (long)nums[i]) <= t) return true;
            }
        }
        return false;
    }

    //使用二叉搜索树,两个重要方法ceiling返回大于目标的最小值 floor返回小于目标的最大值
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i ++) {
            //如果不存在返回null，目标是找范围[nums[i] - t, nums[i] + t]
            Long ceil = set.ceiling((long)nums[i] - t);
            //考虑到还不能超过nums[i] + t的位置
//            if (ceil != null && ceil <= nums[i] + t) {
//                //不为空，说明找到一个大于nums[i] - t的最小值
//                return true;
//            }
            Long flo = set.floor((long)nums[i] + t);
            //需要考虑不能小于nums[i] - t
//            if (flo != null && flo >= nums[i] - t) {
//                //说明存在小于nums[i] + t的值
//                return true;
//            }

            if (ceil != null && flo != null && ceil <= flo) return true;
            set.add((long)nums[i]);
            //如果数量超过区间k，删除最前一个元素
            if (set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    //桶排
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        //用来存储桶id和对应桶内一个元素
        Map<Long, Long> map = new HashMap<>();
        //不符合题意
        if (k <= 0 || t < 0) return false;
        for (int i = 0; i < nums.length; i ++) {
            //但要考虑整体元素长度是否超过限值k，下一伦即将超过就减去最前面一个元素所在桶位置内元素
            if (i > k) map.remove(getId(nums[i - k - 1], t + 1));
            long id = getId(nums[i], (long)t + 1);
            //如果该id桶已经有元素，桶内元素差必小于等于t（width - 1），所以直接返回true
            if (map.containsKey(id)) return true;
            //如果不包含该id，只需要考虑左右两侧id桶内有无元素，且元素之差绝对值是否符合题意
            if ((map.containsKey(id - 1) && Math.abs(map.get(id - 1) - nums[i]) <= t)
                    || (map.containsKey(id + 1) && Math.abs(map.get(id + 1) - nums[i]) <= t)) return true;
            //若上述都不满足，说明该id以及左右两个桶内都没有符合要求的元素，直接添加即可
            map.put(id, (long)nums[i]);
        }
        return false;
    }
    //创建一个方法，根据值和区间长度，确定该值的id位置
    private long getId(long num, long width) {
        return num < 0 ? (num - (width - 1)) / width : num / width;
    }
}
