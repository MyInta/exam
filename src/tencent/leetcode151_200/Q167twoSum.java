package tencent.leetcode151_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/11/22
 * @describe 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 */
public class Q167twoSum {
    //根据题意，我先想到了用map保存每个元素和索引位置，遍历每个元素，找有无target-其v的key即可
    //果不其然，能AC 但是效率低下
    public int[] twoSum(int[] numbers, int target) {
        //返回空数组小技巧，返回int[0]，即代表长度为0的int[]，否则容易出现默认赋值0的情况
        if (numbers == null || numbers.length < 2) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        //本来想要用0的，但是因为下标从1开始，干脆直接加了
        int index = 1;
        for (int num : numbers) {
            map.put(num, index ++);
        }
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i ++) {
            if (map.containsKey(target - numbers[i])) {
                res[0] = i + 1;
                res[1] = map.get(target - numbers[i]);
                break;
            }
        }
        return res;
    }

    //既然都说了是有序数组，那能优化的地方多了，结果速度反而更慢了。。。
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[0];
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i ++) {
            //如果目标值比当前元素小或者差值比当前元素小（等同于小于两倍的当前值）,后面就不用判断了
            if (target < numbers[i] || target < 2 * numbers[i]) break;
            for (int j = i + 1; j < numbers.length; j ++) {
                if (numbers[j] == target - numbers[i]) {
                    res[0] = i + 1;
                    res[1] = j + 1;
                    return res;
                } else if (numbers[j] > target - numbers[i]) {
                    //当差值比当前元素小的时候，后面只会拉大这种差距，直接进入下一循环
                    break;
                }
            }
        }
        return res;
    }

    //根据排序数组的规律，用指针可以更优解
    public int[] twoSum3(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[0];
        int left = 0;
        int right = numbers.length - 1;
        int[] res = new int[2];
        while (left < right) {
            if (numbers[left] + numbers[right] > target) {
                right --;
            } else if (numbers[left] + numbers[right] < target) {
                left ++;
            } else if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            }
        }
        return res;
    }
}
