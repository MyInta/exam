package tencent.leetcode451_500;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/17
 * @describe 给定两个没有重复元素的数组 nums1 和 nums2 ，
 * 其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 注意:
 *
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 *
 */
public class Q496nextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i ++) {
            int index = findIndex(nums2, nums1[i]);
            int new_res = -1;
            for (int j = index + 1; j < nums2.length; j ++) {
                if (nums2[j] > nums1[i]) {
                    new_res = nums2[j];
                    break;
                }
            }
            res[i] = new_res;
        }
        return res;
    }
    private int findIndex(int[] nums2, int num) {
        for (int i = 0; i < nums2.length; i ++) {
            if (nums2[i] == num) return i;
        }
        return -1;
    }

    //看了大神的解答，具有普遍性，比我前面的暴力解可扩展性强
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        //用来保存当前栈内元素最小的元素
        Stack<Integer> stack = new Stack<>();
        //用来保存每个元素当前往右第一个比他大的元素映射关系
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            //当最大的元素发现一个比它大的，就去构建一个映射关系，并且把前面没有映射的也都映射了
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            //但不管有没有搭建映射关系，我们都需要刷新当前元素情况，但能确保绝对是目前栈内剩余元素中最小的
            stack.add(num);
        }
        //考虑到最后会有元素没有被映射，按照题意为映射-1
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        //遍历nums1，将映射关系组建
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i ++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
