package leetcode_inta.leetcode151_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/9/28
 * @describe 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class Q169majorityElement {
    public int majorityElement(int[] nums) {
        int x = nums.length>>1;
        int maxKey = nums[0];
        int maxValue = 1;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i:nums) {
            if (!hm.containsKey(i)) {
                hm.put(i, 1);
                continue;
            }
            int v = hm.get(i) + 1;
            hm.put(i, v);
            if (v > maxValue) {
                maxKey = i;
                maxValue = v;
            }
            //如果统计值大于x（一半数量），直接返回，如果没有下面代码，返回的是数量最大的键
            if (maxValue > x) {
                return maxKey;
            }
        }
        return maxKey;
    }
    //改良了一下的hashMap求解方式
    public int majorityElement3(int[] nums) {
        int x = nums.length>>1;
        Integer res = null;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i:nums) {
            if (!hm.containsKey(i)) {
                hm.put(i, 1);
            } else{
                hm.put(i, hm.get(i) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry:hm.entrySet()) {
            if (entry.getValue() > x) {
               return res = entry.getKey();
            }
        }
        return res;
    }

    //投票法 因为求的是大于一半数量的目标 若它为+1，其他为-1，结果和为正数
    public int majorityElement2(int[] nums) {
        Integer candidate = null;
        int count = 0;
        for (int i : nums) {
            if (count == 0) candidate = i;
            count += candidate == i ? 1 : -1;
        }
        return candidate;
    }
}
