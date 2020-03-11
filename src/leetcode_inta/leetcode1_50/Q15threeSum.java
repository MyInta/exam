package leetcode_inta.leetcode1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/30
 * @describe 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class Q15threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i ++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                List<Integer> list = new ArrayList<>();
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right --;
                } else if (sum < 0) {
                    left ++;
                } else {
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(new ArrayList<>(list));
                    while (left < right && (nums[left + 1] == nums[left] || nums[right - 1] == nums[right])) {
                        if (nums[left + 1] == nums[left]) {
                            left ++;
                        }
                        if (nums[right - 1] == nums[right]) {
                            right --;
                        }
                    }
                    left ++;
                    right --;
                }
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }
}
