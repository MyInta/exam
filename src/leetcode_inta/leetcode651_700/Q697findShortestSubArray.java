package leetcode_inta.leetcode651_700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/10
 * @describe 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 *
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 *
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 *
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 */
public class Q697findShortestSubArray {
    public int findShortestSubArray(int[] nums) {
        int[] counts = new int[50000];
        for (int num : nums) {
            counts[num]++;
        }
        // value为其度值
        int value = -1;
        // 找到度最大
        for (int num : nums) {
            value = Math.max(value, num);
        }
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (counts[nums[i]] == value) {
                if (!left.containsKey(nums[i])) {
                    left.put(nums[i], i);
                }
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (counts[nums[i]] == value) {
                if (!right.containsKey(nums[i])) {
                    right.put(nums[i], i);
                }
            }
        }
        // 此时left和right分别保存了数量重复最多的元素，他们的左右界限情况
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : left.entrySet()) {
            res = Math.min(res, right.get(entry.getKey()) - entry.getValue() + 1);
        }
        return res;
    }

    public int findShortestSubArray2(int[] nums) {
        // 记录元素在数组中的两端位置
        Map<Integer, int[]> distance = new HashMap<>();
        // 记录元素在数组中的数量
        Map<Integer, Integer> counts = new HashMap<>();
        // 记录最大值度
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = counts.getOrDefault(nums[i], 0) + 1;
            max = Math.max(max, value);
            counts.put(nums[i],  value);
            if (!distance.containsKey(nums[i])) {
                int[] temp = new int[2];
                temp[0] = i;
                distance.put(nums[i], temp);
            } else {
                int[] temp = distance.get(nums[i]);
                temp[1] = i;
                distance.put(nums[i], temp);
            }
        }
        // 记录最短连续子数组长度
        int min = nums.length;
        for (Map.Entry<Integer, int[]> entry : distance.entrySet()) {
            if (counts.get(entry.getKey()) == max) {
                int[] temp = entry.getValue();
                int dis = temp[1] > temp[0] ? temp[1] - temp[0] + 1 : 1;
                min = Math.min(min, dis);
            }
        }
        return min;
    }
}
