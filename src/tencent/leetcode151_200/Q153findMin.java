package tencent.leetcode151_200;

/**
 * @author inta
 * @date 2019/12/10
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class Q153findMin {
    //这道题目如果耍无赖，直接遍历找最小即可，但是这样失去数学题的精华了，直觉是用归并,先二分试试
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[right]) {
                //如果大于右值，最小值必在(mid-right)区间中
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                //如果小于右值,最小值必在[left, mid]区间中
                right = mid;
            }
            //mid可能和right相等吗？只有当right与left相差为0才行，即跳出while
        }
        return nums[left];
    }
}
