package leetcode_inta.leetcode1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/17
 * @describe 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */
public class Q18fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums == null || nums.length < 4) return res;
        int len = nums.length;
        //记录四个索引 i j n m 并且最后n m在一个循环里
        for (int i = 0; i < nums.length - 3; i ++) {

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int min = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min > target) break;
            int max = nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1];
            if (max < target) continue;

            for (int j = i + 1; j < nums.length - 2; j ++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                min = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min > target) break;
                max = nums[i] + nums[j] + nums[len - 2] + nums[len - 1];
                if (max < target) continue;

                int n = j + 1;
                int m = nums.length - 1;
                while (n < m) {
                    //此时的min只是个工具，只表示元素和
                    min = nums[i] + nums[j] + nums[n] + nums[m];
                    if (min > target) {
                        m --;
                    } else if (min < target) {
                        n ++;
                    } else if (min == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[n], nums[m]));
                        n ++;
                        while (n < m && nums[n] == nums[n - 1]) {
                            n ++;
                        }
                        m --;
                        while (m > n && nums[m] == nums[m + 1]) {
                            m --;
                        }
                    }
                }
            }
        }
        return res;
    }
}
