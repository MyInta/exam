package tencent.leetcode401_450;

/**
 * @author inta
 * @date 2019/12/5
 * @describe 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 */
public class Q415addStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int high = 0;
//        char[] c_one = num1.toCharArray();
//        char[] c_two = num2.toCharArray();
//        int len1 = c_one.length - 1;
//        int len2 = c_two.length - 1;
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        while (len1 >= 0 || len2 >= 0) {
//            int one_v = len1 < 0 ? 0 : c_one[len1] - '0';
//            int two_v = len2 < 0 ? 0 : c_two[len2] - '0';
            int one_v = len1 < 0 ? 0 : num1.charAt(len1) - '0';
            int two_v = len2 < 0 ? 0 : num2.charAt(len2) - '0';
            int sum = one_v + two_v + high;
            high = sum / 10;
            sb.append(sum % 10);
            len1 --;
            len2 --;
        }
        if (high != 0) sb.append(high);
        return sb.reverse().toString();
    }
}
