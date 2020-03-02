package tencent.leetcode1_50;

import java.awt.image.renderable.RenderableImage;

/**
 * @author inta
 * @date 2019/10/1
 * @describe 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 */
public class Q33search {
    public int search(int[] nums, int target) {
        return solution(nums, 0, nums.length - 1, target);
    }

    private int solution(int[] nums, int left, int right, int target) {
        if (left == right && nums[left] == target) {
            return left;
        }
        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            int l = solution(nums, left, mid, target);
            int r = solution(nums, mid + 1, right, target);
            if (l != -1) {
                return l;
            }
            if (r != -1) {
                return r;
            }
            return -1;
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        return solution(nums, 0, nums.length - 1, target);
    }
    private int solution2(int[] nums, int left, int right, int target) {
        if (left == right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = ((right - left) >> 1) + left;
        return Math.max(solution(nums, left, mid, target), solution(nums, mid + 1, right, target));
    }

    //听说这道题目有点费脑子，时隔四个月我来试试
    public int search3(int[] nums, int target) {
         if (nums.length == 0) return -1;
         return binarySearch(nums, 0, nums.length - 1, target);
    }
    private int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = left + ((right - left) >> 1);
        if (nums[mid] > target) {
            //中间值大于目标值，考虑目标值可能在前半段还是后半段
            if (nums[mid] >= nums[left]) {
                //出现该可能说明left-mid是升序段
                if (nums[left] > target) {
                    return binarySearch(nums, mid + 1, right, target);
                } else if (nums[left] < target) {
                    return binarySearch(nums, left, mid - 1, target);
                } else {
                    return left;
                }
            } else {
                //否则说明拐点在前半段,后半段升序，后半段都大于目标值，只能在前半段找
                return binarySearch(nums, left, mid - 1, target);
            }
        } else if (nums[mid] < target) {
            //如果中间值比目标值小，同上考虑在前半段还是后半段
            if (nums[mid] >= nums[left]) {
                //说明这个中间值是在升序段落内，那直接考虑后半段即可
                return binarySearch(nums, mid + 1, right, target);
            } else {
                //否则，说明前半段存在拐弯点，目标可能在left-拐弯点之间或者后半段
                //因为后半段是升序的，还可以再筛一下
                if (nums[right] > target) {
                    return binarySearch(nums, mid + 1, right, target);
                } else if (nums[right] < target) {
                    return binarySearch(nums, left, mid - 1, target);
                } else {
                    return right;
                }
            }
        } else {
            return mid;
        }
    }
}
