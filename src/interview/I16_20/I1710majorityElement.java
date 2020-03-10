package interview.I16_20;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/10
 * @describe

如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

示例 1：

输入：[1,2,5,9,5,9,5,5,5]
输出：5


示例 2：

输入：[3,2]
输出：-1


示例 3：

输入：[2,2,1,1,1,2,2]
输出：2

说明：
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 */
public class I1710majorityElement {
    public int majorityElement(int[] nums) {
        //先考虑下最简单的做法，再考虑优化
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > len / 2) return num;
        }
        return -1;
    }

    //思路，第一遍摩尔投票找到数量最多的那个，第二遍数数量是否大于一半
    public int majorityElement2(int[] nums) {
        if (nums == null) return -1;
        //摩尔投票
        int res = nums[0];
        int count = 0;
        for (int num : nums) {
            if (num == res) {
                count ++;
            } else {
                count --;
                if (count == 0) {
                    res = num;
                    count = 1;
                }
            }
        }
        int countRes = 0;
        for (int num : nums) {
            if (num == res) countRes ++;
        }
        return countRes > nums.length / 2 ? res : -1;
    }
}
