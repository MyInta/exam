package leetcode_inta.exam_main.leetcode_exam2021.D67;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2021/12/11
 * @describe
 */
public class Q1 {
    // 找k个值之和最大，排序区间搞定
    public int[] maxSubsequence(int[] nums, int k) {
        int[] copyArr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyArr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = copyArr.length - 1; i >= copyArr.length - k; i--) {
            map.put(copyArr[i], map.getOrDefault(copyArr[i], 0) + 1);
        }
        int[] res = new int[k];
        int index = 0;
        for (int num : nums) {
            if (map.getOrDefault(num, 0) > 0) {
                res[index++] = num;
                map.put(num, map.get(num) - 1);
            }
        }
        return res;
    }
}
