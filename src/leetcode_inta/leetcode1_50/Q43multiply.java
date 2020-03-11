package leetcode_inta.leetcode1_50;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/9/30
 * @describe 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */
public class Q43multiply {
    public String multiply(String num1, String num2) {
        char[] res = new char[num1.length() + num2.length()];
        Arrays.fill(res, '0');
        for (int i = num1.length() - 1; i >= 0; i --) {
            for (int j = num2.length() - 1; j >= 0; j --) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[i + j + 1] - '0';
                res[i + j + 1] = (char) (sum % 10 + '0');
                res[i + j] += sum / 10;
            }
        }
        for (int i = 0; i < res.length; i ++) {
            if (res[i] != '0' || i == res.length - 1) {
                return new String(res, i, res.length - i);
            }
        }
        return "0";
    }
}
