package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/10/16
 * @describe 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 */
public class Q171titleToNumber {
    public int titleToNumber(String s) {
        int high = s.length();
        if (high == 0) return 0;
        int res = 0;
        for (int i = 0; i < high - 1; i ++) {
            int temp = s.charAt(i) - 'A' + 1;
            for (int j = 0; j < high - i; j ++) {
                temp *= 26;
            }
            res += temp;
        }
        return res;
    }

    //高效一点的
    public int titleToNumber2(String s) {
        int high = s.length();
        if (high == 0) return 0;
        int res = 0;
        for (int i = 0; i < high; i ++) {
            res = res * 26 + s.charAt(i) - 'A' + 1;
        }
        return res;
    }
}
