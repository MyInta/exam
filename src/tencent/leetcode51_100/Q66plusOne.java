package tencent.leetcode51_100;

import java.util.ArrayList;

/**
 * @author inta
 * @date 2019/10/31
 * @describe 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 */
public class Q66plusOne {
    public int[] plusOne(int[] digits) {
        ArrayList<Integer> nums = new ArrayList<>();
        int addNum = 1;
        for (int i = digits.length - 1; i >= 0; i --) {
            int temp = (digits[i] + addNum) % 10;
            addNum = (digits[i] + addNum) / 10;
            nums.add(temp);
        }
        if (addNum != 0) nums.add(addNum);
        int[] res = new int[nums.size()];
        for (int i = 0; i < res.length; i ++) {
            res[i] = nums.get(res.length - i - 1);
        }
        return res;
    }

    //根据题意得优化,只要考虑末端有没有进位，没有就直接返回，最后考虑下位数变化
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i --) {
            digits[i] ++;
            digits[i] = digits[i] % 10;
            //如果不为0，即没有进位，既然已经改变数值，直接返回即可
            if (digits[i] != 0) return digits;
        }
        //考虑下最后进位,题目特殊性，直接返回1, 0, ... ...即可
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
