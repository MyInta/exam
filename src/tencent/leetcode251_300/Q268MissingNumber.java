package tencent.leetcode251_300;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/9/5
 * @describe 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 */
public class Q268MissingNumber {
    //此法适用于题干中0-N非连续数组中，寻找缺失的第一个数字，根据本题意这么求解效率低了
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int cur = 0;
        while (cur <= len-1) {
            if (nums[cur] > len-1) {
                nums[cur] = nums[len - 1];
                len--;
            } else if (nums[cur] > cur) {
                //交换
                int temp = nums[cur];
                nums[cur] = nums[temp];
                nums[temp] = temp;
            } else {
                //即nums[cur] == cur的时候
                cur++;
            }
        }
        return cur;
    }

    //使用排序算差值与1的关系可以求到缺失数字 结果时间复杂度更高了，因为是nlogn
    public int missingNumber2(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        //排除首位0
        if (nums[0] != 0) return 0;
        //排除缺失n
        if (nums[len - 1] != len) {
            return len;
        }
        //再考虑0-n之间缺失
        for (int i = 0; i < len - 1; i++) {
            if (nums[i + 1] - nums[i] > 1) {
                return i + 1;
            }
        }
        //如果没找到，说明不存在
        return -1;
    }

    //题目特殊性，计算得到
    public int missingNumber3(int[] nums) {
        int res = 0;
        for (int i = 0; i< nums.length; i++) {
            res += i - nums[i];
        }
        return res + nums.length;
    }


}
