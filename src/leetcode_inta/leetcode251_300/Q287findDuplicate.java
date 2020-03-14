package leetcode_inta.leetcode251_300;

/**
 * @author inta
 * @date 2019/10/5
 * @describe 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 */
public class Q287findDuplicate {
    //使用二分查找
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            int count = 0;
            //找到数组中所有小于等于mid的个数
            for (int num : nums) {
                if (num <= mid) {
                    count ++;
                }
            }
            //如果这个个数小于等于mid，重复数在右区域，否则在左区域
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
