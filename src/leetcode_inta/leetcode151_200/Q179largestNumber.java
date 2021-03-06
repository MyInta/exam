package leetcode_inta.leetcode151_200;

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
    public String largestNumber2 (int[] nums) {
        if (nums == null || nums.length == 0) return "0";
        String[] numsStr = new String[nums.length];
        int index = 0;
        for (int num : nums) {
            numsStr[index ++] = String.valueOf(num);
        }
        Arrays.sort(numsStr, (a, b)->(b + a).compareTo(a + b));
        if (numsStr[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : numsStr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
