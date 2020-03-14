package leetcode_inta.leetcode201_250;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author inta
 * @date 2019/9/26
 * @describe 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class Q217containsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hm = new HashSet<>();
        for (int i:nums) {
            if (hm.contains(i)) {
                return true;
            }
            hm.add(i);
        }
        return false;
    }

    //效率低，但一行的思路 挺好玩的
    public boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

}
