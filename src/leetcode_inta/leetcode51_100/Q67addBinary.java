package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/10
 * @describe 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 */
public class Q67addBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int index_a = a.length() - 1;
        int index_b = b.length() - 1;
        int temp = 0;
        while (index_a >= 0 && index_b >= 0) {
            if (a.charAt(index_a) == b.charAt(index_b)) {
                if (a.charAt(index_a) == '1') {
                    sb.append(temp);
                    temp = 1;
                } else {
                    sb.append(temp);
                    temp = 0;
                }
            } else {
                if (temp == 1) {
                    sb.append(0);
                } else {
                    sb.append(1);
                }
            }
            index_a --;
            index_b --;
        }
        while (index_a >= 0) {
            if (a.charAt(index_a) == '1' && temp == 1) {
                sb.append(0);
            } else if (a.charAt(index_a) == '0' && temp == 1) {
                sb.append(1);
                temp = 0;
            } else {
                sb.append(a.charAt(index_a));
            }
            index_a --;
        }

        while (index_b >= 0) {
            if (b.charAt(index_b) == '1' && temp == 1) {
                sb.append(0);
            } else if (b.charAt(index_b) == '0' && temp == 1) {
                sb.append(1);
                temp = 0;
            } else {
                sb.append(b.charAt(index_b));
            }
            index_b --;
        }
        if (temp != 0) sb.append(1);

        return sb.reverse().toString();
    }

    //我的解法速度飞快，但是代码臃肿，看了网上大神的解法，用0补全较短字符，醍醐灌顶
    public String addBinary2(String a, String b) {
        //记录累加之后是否进位的值
        int heigh = 0;
        //用来从后往前累加字符串
        StringBuilder sb = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i --, j --) {
            //每一次新的累加都需要先加上进位
            int sum = heigh;
            //只要索引小于0，就是缺省部分，我们用0补全
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            //2进制，保留小于2的部分
            sb.append(sum % 2);
            //更新进位
            heigh = sum / 2;
        }
        //最后要提防还有进位的存在
        if (heigh > 0) sb.append(1);
        return sb.reverse().toString();
    }

}
