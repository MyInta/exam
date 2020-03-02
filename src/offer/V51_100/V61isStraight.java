package offer.V51_100;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/3/2
 * @describe 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *  
 *
 * 限制：
 *
 * 数组长度为 5 
 *
 * 数组的数取值为 [0, 13] .
 */
public class V61isStraight {
    public boolean isStraight(int[] nums) {
        //统计有多少个0
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < 4; i ++) {
            if (nums[i] == 0) {
                count ++;
            } else {
                if (nums[i + 1] == nums[i]) return false;
                count -= nums[i + 1] - nums[i] - 1;
            }
        }
        return count >= 0;
    }
}
