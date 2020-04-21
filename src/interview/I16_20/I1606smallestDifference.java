package interview.I16_20;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/4/20
 * @describe 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 *
 * 示例：
 *
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出： 3，即数值对(11, 8)
 *
 * 提示：
 *
 *     1 <= a.length, b.length <= 100000
 *     -2147483648 <= a[i], b[i] <= 2147483647
 *     正确结果在区间[-2147483648, 2147483647]内
 *
 */
public class I1606smallestDifference {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        long min = Integer.MAX_VALUE;
        int end = a.length;
        for (int i : b) {
            int index = find(a, i);
            if (index < end) {
                min = Math.min(min, Math.abs((long)i - a[index]));
            }
            if (index > 0) {
                min = Math.min(min, Math.abs((long)i - a[index - 1]));
            }
        }
        return (int)min;
    }
    //找nums中最小的大于target的索引位置
    private int find(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    //看大佬是直接两个排序双指针做的，核心是排序后从两者最小的元素互相比较的到
    public int smallestDifference2(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long res = Integer.MAX_VALUE;
        int i = 0, j = 0, a_size = a.length, b_size = b.length;
        while (i < a_size && j < b_size) {
            res = Math.min(res, Math.abs((long)a[i] - b[j]));
            if (a[i] < b[j]) {
                i ++;
            } else {
                j ++;
            }
        }
        return (int)res;
    }
}
