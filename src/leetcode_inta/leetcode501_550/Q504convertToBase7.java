package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2020/6/3
 * @describe 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 *
 * 输入: 100
 * 输出: "202"
 *
 * 示例 2:
 *
 * 输入: -7
 * 输出: "-10"
 *
 * 注意: 输入范围是 [-1e7, 1e7] 。
 *
 */
public class Q504convertToBase7 {
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean flag = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (flag) sb.append("-");
        return sb.reverse().toString();
    }
}
