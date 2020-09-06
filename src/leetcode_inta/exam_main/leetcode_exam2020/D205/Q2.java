package leetcode_inta.exam_main.leetcode_exam2020.D205;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2020/9/6
 * @describe
 */
public class Q2 {

    public int numTriplets(int[] nums1, int[] nums2) {
       int res = 0;
       res += getCount(nums1, nums2);
       res += getCount(nums2, nums1);
       return res;
    }
    private int getCount(int[] nums1, int[] nums2) {
        int res = 0;
        Map<Long, Integer> map_nums2 = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                long key = (long)nums2[i] * nums2[j];
                map_nums2.put(key, map_nums2.getOrDefault(key, 0) + 1);
            }
        }
        for (int i = 0; i < nums1.length; i ++) {
            long key = (long)nums1[i] * nums1[i];
            if (map_nums2.containsKey(key)) res += map_nums2.get(key);
        }
        return res;
    }
}
