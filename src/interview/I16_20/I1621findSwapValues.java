package interview.I16_20;

import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/3/27
 * @describe 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
 * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 *
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 *
 * 示例:
 *
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 *
 * 提示：
 *
 *     1 <= array1.length, array2.length <= 100000
 *
 */
public class I1621findSwapValues {
    //使用set保存，蠢办法，实现比较容易
    public int[] findSwapValues(int[] array1, int[] array2) {
        //先考虑两数组和
        int sum1 = 0, sum2 = 0, sum;
        //用来保存array2中所有独立的数字
        Set<Integer> set = new HashSet<>();
        for (int array : array1) sum1 += array;
        for (int array : array2) {
            sum2 += array;
            set.add(array);
        }
        sum = sum1 + sum2;
        if (sum % 2 == 1) return new int[0];
        //这是数组1缺失的可以达到平衡的数量
        int target = sum / 2 - sum1;
        //遍历数组1的每个元素，考虑其与target和能否在set中找到
        for (int i = 0; i < array1.length; i++) {
            if (set.contains(array1[i] + target)) return new int[]{array1[i], array1[i] + target};
        }
        return new int[0];
    }
}
