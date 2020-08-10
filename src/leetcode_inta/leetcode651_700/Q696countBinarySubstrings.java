package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2020/8/10
 * @describe 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 *
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 *
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *
 * 示例 2 :
 *
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 *
 * 注意：
 *
 *     s.length 在1到50,000之间。
 *     s 只包含“0”或“1”字符。
 *
 */
public class Q696countBinarySubstrings {
    //思路：找0或者1连续的数量，遇到不相同的，统计后者连续数量，取两者最小值为可拼凑最大值，类推
    public int countBinarySubstrings(String s) {
        int pre = 0, cur = 0, res = 0;
        char temp = s.charAt(0);
        for (char c : s.toCharArray()) {
            if (c == temp) {
                cur++;
            } else {
                res += Math.min(pre, cur);
                temp = c;
                pre = cur;
                cur = 1;
            }
        }
        //最后考虑一下，边界到了末端，却没有统计数量的情况
        res += Math.min(pre, cur);
        return res;
    }
}
