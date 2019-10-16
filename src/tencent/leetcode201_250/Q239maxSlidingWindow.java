package tencent.leetcode201_250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/16
 * @describe 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 *  
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 */
public class Q239maxSlidingWindow {
    //非线性的解法来一个
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            int[] res = {};
            return res;
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i ++) {
            res[i] = searchMax(nums, i, i + k);
        }
        return res;
    }
    //找到最大值的方法
    private int searchMax(int[] nums, int start, int end) {
        int maxNum = nums[start];
        for (int i = start + 1; i < end; i ++) {
            if (nums[i] > maxNum) {
                maxNum = nums[i];
            }
        }
        return maxNum;
    }

    //滑窗的动态求解。使用两个数组，分别在最左和最右存储切割单元的最大值
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        //按k为长度切割小块，left存储右侧max，right存储左侧max，两者合一可以保存数组所有状态
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i ++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            int j = n - i - 1;
            //因为是索引，如k=3 0-2的话，i取0，而j需要j + 1
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                if (j == n - 1) {
                    right[j] = nums[j];
                } else {
                    right[j] = Math.max(right[j + 1], nums[j]);
                }
            }
        }
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i ++) {
            res[i] = Math.max(left[i + k - 1], right[i]);
        }
        return res;
    }
}
