package leetcode_inta.leetcode301_350;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2019/11/27
 * @describe 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Q349intersection {
    //使用set去重
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int cur1 = 0;
        int len2 = nums2.length;
        int cur2 = 0;
        Set<Integer> res = new HashSet<>();
        while (cur1 < len1 && cur2 < len2) {
            if (nums1[cur1] < nums2[cur2]) {
                cur1 ++;
            } else if (nums1[cur1] == nums2[cur2]) {
                res.add(nums1[cur1]);
                cur1 ++;
                cur2 ++;
            } else if (nums1[cur1] > nums2[cur2]) {
                cur2 ++;
            }
        }
        int[] to_res = new int[res.size()];
        cur1 = 0;
        for (int i : res) {
            to_res[cur1 ++] = i;
        }
        return to_res;
    }

}
