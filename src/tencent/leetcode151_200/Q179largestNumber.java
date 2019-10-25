package tencent.leetcode151_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author inta
 * @date 2019/10/25
 * @describe 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 */
public class Q179largestNumber{
    private class largestNumber_comparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o2 + o1).compareTo(o1 + o2);
        }
    }
    public String largestNumber (int[] nums) {
        if (nums == null || nums.length == 0) return "0";
        String[] numsStr = new String[nums.length];
        int index = 0;
        for (int num : nums) {
            numsStr[index ++] = String.valueOf(num);
        }
        Arrays.sort(numsStr, new largestNumber_comparator());
        if (numsStr[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i ++) {
            sb.append(numsStr[i]);
        }
        return sb.toString();
    }
}
