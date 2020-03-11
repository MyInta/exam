package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/2
 * @describe 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Q4findMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums3 = new int[m + n];
        System.arraycopy(nums1, 0, nums3, 0, m);
        while (m > 0 && n > 0) {
            nums3[m + n - 1] = nums2[n - 1] > nums3[m - 1] ? nums2[n-- - 1] : nums3[m-- - 1];
        }
        System.arraycopy(nums2, 0, nums3, 0, n);
        if (nums3.length % 2 == 1) {
            return (double) nums3[nums3.length >> 1];
        } else {
            return (double) (nums3[nums3.length >> 1] + nums3[(nums3.length >> 1) - 1]) / 2;
        }
    }
}
