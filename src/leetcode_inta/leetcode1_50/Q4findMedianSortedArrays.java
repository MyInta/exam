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

    //二分法来实现log(m+n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //考虑数组长度之和是否是奇数，有个取巧方式，获得奇数情况下的两倍中间值或偶数情况下的中间两值
        return (solution(nums1, nums2, 0, 0, (m + n + 1) / 2) +
                solution(nums1, nums2, 0, 0, (m + n + 2) / 2)) / 2.0;
    }
    //遍历两个数组，比较各自中间值，递归到只剩要找一个元素，或者其中一个数组为空,i是数组1索引，j数组2索引,要找第K个数
    private int solution(int[] nums1, int[] nums2, int i, int j, int K) {
        //由题意分析可以知道一定存在中位数，不用担心数组越界
        if (i >= nums1.length) return nums2[j + K - 1];
        if (j >= nums2.length) return nums1[i + K - 1];
        //如果只剩一个要找，就是最小的那一个
        if (K == 1) return Math.min(nums1[i], nums2[j]);
        //否则就一段段砍半，存在越界的情况，就假定其为最大值，我们必定需要砍掉一半，越界说明要砍的在另一个数组,所以设最大值方便砍
        int nums1_mid_v = i + K / 2 - 1 < nums1.length ? nums1[i + K / 2 - 1] : Integer.MAX_VALUE;
        int nums2_mid_v = j + K / 2 - 1 < nums2.length ? nums2[j + K / 2 - 1] : Integer.MAX_VALUE;
        return nums1_mid_v <= nums2_mid_v ? solution(nums1, nums2, i + K / 2, j, K - K / 2) :
                solution(nums1, nums2, i, j + K / 2, K - K / 2);
    }
}
