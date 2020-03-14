package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/12/20
 * @describe 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * 示例 1:
 *
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 *
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 *
 * 输入: 701
 * 输出: "ZY"
 *
 */
public class Q168convertToTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int last;
        while (n > 0) {
            last = n % 26;
            //当余数为0，认为其为26号
            if (last == 0) last = 26;
            sb.append((char)('A' + last - 1));
            //减去末端数，并缩小原值
            n = (n - last) / 26;
        }
        return sb.reverse().toString();
    }
}
