package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/11/23
 * @describe 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 */
public class Q6convert {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 1 || numRows == s.length()) return s;
        StringBuilder sb = new StringBuilder();
        //s字符的长度,用来判断可以分成几段
        int len = s.length();
        //首行
        int first = 0;
        while (first < len) {
            sb.append(s.charAt(first));
            //更新奇数段索引位置
            first += 2 * (numRows - 1);
        }
        //对于中间层情况
        for (int i = 1; i < numRows - 1; i ++) {
            //记录奇数段落的索引
            int index = i;
            //记录偶数段落的索引 numRows -1为段落长度
            int index_2 = 2 * (numRows - 1) - i;
            //记录是否为奇数段落（顺序，偶数段落为逆序）
            boolean flag = true;
            while (index < len || index_2 < len) {
                if (flag) {
                    sb.append(s.charAt(index));
                    //更新奇数段索引位置
                    index += 2 * (numRows - 1);
                } else {
                    sb.append(s.charAt(index_2));
                    index_2 += 2 * (numRows - 1);
                }
                flag = !flag;
            }
        }
        //最后一行
        int last = numRows - 1;
        while (last < len) {
            sb.append(s.charAt(last));
            last += 2 * (numRows - 1);
        }
        return sb.toString();
    }
}
