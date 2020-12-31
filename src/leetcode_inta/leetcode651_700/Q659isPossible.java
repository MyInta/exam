package leetcode_inta.leetcode651_700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/12/4
 * @describe 给你一个按升序排序的整数数组 num（可能包含重复数字），
 * 请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 *
 * 提示：
 *
 * 输入的数组长度范围为 [1, 10000]
 *
 */
public class Q659isPossible {
    //看了评论区后的思路，使用map维护元素个数，存在下一个元素数量大于当前元素时候进行删减
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            int sub = 0;
            int pre = 1;
            int next = num;
            //如果遇到后一个元素数量大于等于前者时候，我们抽离出来当作一个子序列
            while (map.getOrDefault(next, 0) >= pre) {
                //每次抽离的时候，指针往后移
                pre = map.get(next);
                //使用过的元素消减一个
                map.put(next, pre - 1);
                //形成的子序列长度增加
                sub++;
                //遍历下一个值连续的元素
                next++;
            }
            //跳出循环的时候进行判断，形成的子序列是否长度满足>=3，需要考虑等于0情况
            if (sub > 0 && sub < 3) return false;
        }
        //最终都符合条件下，说明可以被拆分为题意要求的子序列
        return true;
    }
}
