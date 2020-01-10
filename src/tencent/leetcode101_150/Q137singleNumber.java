package tencent.leetcode101_150;


import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/7
 * @describe 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 *
 */
public class Q137singleNumber {
    //官解使用了位运算，考虑32位即可
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i ++) {
            //统计非0位置bit出现次数
            int count_bit = 0;
            int bit = 1 << i;
            for (int num : nums) {
                if ((num & bit) != 0) count_bit ++;
            }
            if (count_bit % 3 != 0) res |= bit;
        }
        return res;
    }

    //若不考虑空间优化，而使用传统方式，用数据结构保存来做
    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }
}
