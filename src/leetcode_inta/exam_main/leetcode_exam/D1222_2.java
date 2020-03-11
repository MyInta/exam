package leetcode_inta.exam_main.leetcode_exam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/22
 * @describe
 */
public class D1222_2 {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i ++) {
            if (map.get(nums[i]) > 0) {
                int index = 0;
                while (index < k) {
                    if (!map.containsKey(nums[i] + index) || map.get(nums[i] + index) <= 0) return false;
                    map.put(nums[i] + index, map.get(nums[i] + index) - 1);
                    index ++;
                }
            }
        }
        return true;
    }
}
