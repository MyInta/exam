package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/21
 * @describe 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 *
 */
public class Q38countAndSay {
    public String countAndSay(int n) {
        String res = "1";
        while (n > 1) {
            char[] res_Chars = res.toCharArray();
            int len = res_Chars.length;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i ++) {
                int count = 1;
                char temp = res_Chars[i];
                while (i < len - 1 && res_Chars[i + 1] == res_Chars[i]) {
                    count ++;
                    i ++;
                }
                sb.append(count).append(temp);
            }
            res = sb.toString();
            n --;
        }
        return res;
    }
}
