package tencent.leetcode51_100;

/**
 * @author inta
 * @date 2019/10/2
 * @describe 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class Q88merge {
    //依次遍历最后面那个，选择性添加上去
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0) {
            if (nums2[n - 1] > nums1[m - 1]) {
                nums1[m + n - 1] = nums2[n - 1];
                n --;
            } else {
                nums1[m + n - 1] = nums1[m - 1];
                m --;
            }
        }
        if (m == 0) {
            for (int k = 0; k < n; k++) {
                nums1[k] = nums2[k];
            }
        }
    }
}
