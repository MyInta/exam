package leetcode_inta.leetcode201_250;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/26
 * @describe 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 
 * 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * 提示：
 *
 * 0 <= nums.length <= 2 * 10^4
 * -2^3^=1 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 */
public class Q220containsNearbyAlmostDuplicate {
    // 首先想到暴力法,效率极低是肯定的
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long)nums[j] - (long)nums[i]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    // 使用二叉搜索树,两个重要方法ceiling返回大于目标的最小值 floor返回小于目标的最大值
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i ++) {
            // 如果不存在返回null，目标是找范围[nums[i] - t, nums[i] + t]
            Long ceil = set.ceiling((long)nums[i] - t);
            Long flo = set.floor((long)nums[i] + t);
            if (ceil != null && flo != null && ceil <= flo) {
                return true;
            }
            set.add((long)nums[i]);
            // 如果数量超过区间k，删除最前一个元素
            if (set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    // 桶排,原理就是保存一个窗口，在窗口内找当前元素如果分配到桶中(桶宽度<=t,即[t+1)长度)的索引是否已经有元素，或者与前后两桶元素比较
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        // 用来存储桶id和对应桶内一个元素
        Map<Long, Long> map = new HashMap<>();
        // 不符合题意
        if (k == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            // 但要考虑整体元素索引差值是否超过限值k(满距离为k+1)，下一伦即将超过就减去最前面一个元素所在桶位置内元素（维持长度k+1的map窗口）
            if (i > k) map.remove(getId(nums[i - k - 1], t + 1));
            long id = getId(nums[i], (long)t + 1);
            // 如果该id桶已经有元素，桶内元素差必小于等于t（width - 1），所以直接返回true
            if (map.containsKey(id)) {
                return true;
            }
            // 如果不包含该id，只需要考虑左右两侧id桶内有无元素，且元素之差绝对值是否符合题意
            if ((map.containsKey(id - 1) && Math.abs(map.get(id - 1) - nums[i]) <= t)
                    || (map.containsKey(id + 1) && Math.abs(map.get(id + 1) - nums[i]) <= t)) {
                return true;
            }
            //若上述都不满足，说明该id以及左右两个桶内都没有符合要求的元素，直接添加即可
            map.put(id, (long)nums[i]);
        }
        return false;
    }
    //创建一个方法，根据值和区间长度，确定该值的id位置,这里长度其实是固定的t+1是因为我们的桶长度[t+1)可以满足长度<=t
    //当负值时，如-3/3 我们应该返回-2同理-2/3返回-1，为此针对java计算方式，需要将负值延伸分母-1的距离才可以做到
    private long getId(long num, long width) {
        return num < 0 ? (num - (width - 1)) / width : num / width;
    }
}
