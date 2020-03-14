package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/29
 * @describe 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 */
public class Q189rotate {
    //旋转各个分段，再整体旋转即可
    public void rotate(int[] nums, int k) {
        if (k == 0 || nums == null || nums.length == 0) return;
        int len = nums.length;
        //k可能非常大，需要先给它消减到长度范围内
        int real_k = k % len;
        for (int i = 0; i < ((len - real_k) >> 1); i ++) {
            swap(i, len - real_k - i - 1, nums);
        }
        for (int i = 0; i < (len >> 1); i ++) {
            swap(i, len - i - 1, nums);
        }
        for (int i = 0; i < (real_k >> 1); i ++) {
            swap(i, real_k - i - 1, nums);
        }

    }

    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //看了大神的操作，发现可以很简单的
    public void rotate2(int[] nums, int k) {
        if (k == 0 || nums == null || nums.length == 0) return;
        k %= nums.length;
        reverse(0, nums.length - 1, nums);
        reverse(0, k - 1, nums);
        reverse(k, nums.length - 1, nums);
    }
    private void reverse(int start, int end, int[] nums) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end --;
        }
    }
}
