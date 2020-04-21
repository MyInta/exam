package interview.I16_20;

import java.util.*;

/**
 * @author inta
 * @date 2020/4/21
 * @describe 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * 示例 1:
 *
 * 输入: nums = [5,6,5], target = 11
 * 输出: [[5,6]]
 *
 * 示例 2:
 *
 * 输入: nums = [5,6,5,6], target = 11
 * 输出: [[5,6],[5,6]]
 *
 * 提示：
 *
 *     nums.length <= 100000
 *
 */
public class I1624pairSums {
    //简单点的思路，就是罗列所有元素和他们的数量，然后找target-目标元素的数量是否>0有就添加到list中
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.getOrDefault(num, 0) != 0 && map.getOrDefault(target - num, 0) != 0) {
                if (2 * num == target && map.get(num) == 1) continue;
                List<Integer> temp = new ArrayList<>();
                temp.add(num);
                temp.add(target - num);
                res.add(temp);
                map.put(num, map.get(num) - 1);
                map.put(target - num, map.get(target - num) - 1);
            }
        }
        return res;
    }

    public List<List<Integer>> pairSums2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            //寻找目标值是否有，没有就说明这个元素相当于还没组合成功过，添加到map中
            int count = map.getOrDefault(target - num, 0);
            if (count != 0) {
                //如果有目标对象，就添加，并且记得数量消除下
                res.add(Arrays.asList(num, target - num));
                map.put(target - num, count - 1);
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return res;
    }

    //还有更快的方式，排序后使用双指针求即可
    public List<List<Integer>> pairSums3(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right --;
            } else if (sum < target) {
                left ++;
            } else {
                res.add(Arrays.asList(nums[left], nums[right]));
                left ++;
                right --;
            }
        }
        return res;
    }
}
