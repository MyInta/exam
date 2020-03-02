package offer.V51_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/1
 * @describe 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *  
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 */
public class V56_2singleNumber {
    //先用蠢办法做一下吧 map保存数量，再次遍历找到目标
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    //官解用的位运算，统计31位位置上的01情况
    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i ++) {
            int bit = 1 << i;
            //统计该位置上1出现次数
            int countbit = 0;
            for (int num : nums) {
                System.out.println(num & bit);
                if ((num & bit) != 0) countbit ++;
            }
            if (countbit % 3 != 0) {
                //说明该位置是有单独数二进制1存储位置
                res |= bit;
            }
        }
        return res;
    }
}
