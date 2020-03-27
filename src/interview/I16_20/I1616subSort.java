package interview.I16_20;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/3/27
 * @describe 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，
 * 整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
 * 函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 *
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 *
 * 提示：
 *
 *     0 <= len(array) <= 1000000
 *
 */
public class I1616subSort {
    public int[] subSort(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        int left = -1, right = -1;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != array[i]) {
                left = i;
                break;
            }
        }
        for (int j = copy.length - 1; j >= 0; j--) {
            if (copy[j] != array[j]) {
                right = j;
                break;
            }
        }
        return new int[]{left, right};
    }

    //大神的思路很清晰，但是我之前考虑得很复杂，原来越简单的流程，精髓越深
    public int[] subSort2(int[] array) {
        //从左到右遍历找比当前最大值小的索引位置更新，该索引位置不该出现在最大值右边，是需要更改位置的最右侧
        //从右到左同上原理，找出左侧大于当前最小值索引，它不应该在最小值左侧
        int left = -1, right = -1;
        if (array.length == 0) return new int[]{left, right};
        int len = array.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = array[i];
            }
        }
        for (int j = len - 1; j >= 0; j--) {
            if (array[j] > min) {
                left = j;
            } else {
                min = array[j];
            }
        }
        return new int[]{left, right};
    }
}
