package leetcode_inta.leetcode301_350;

/**
 * @author inta
 * @date 2019/10/31
 * @describe 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 数学表达式如下:
 *
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: true
 * 示例 2:
 *
 * 输入: [5,4,3,2,1]
 * 输出: false
 *
 */
public class Q334increasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null) return false;
        int min_num = Integer.MAX_VALUE;
        int mid_num = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min_num) {
                //如果遍历到比最小更小的，赋值给最小
                min_num = num;
            } else if (num <= mid_num) {
                //如果比最小大，但是比中间值小，那么将中间值赋值
                mid_num = num;
            } else {
                //如果出现比最小值大，又比中间值大，即存在三个数构成的子序列递增
                return true;
            }
        }
        return false;
    }
}
