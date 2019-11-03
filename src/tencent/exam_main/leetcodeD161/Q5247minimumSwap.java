package tencent.exam_main.leetcodeD161;


/**
 * @author inta
 * @date 2019/11/3
 * @describe 有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
 *
 * 每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
 *
 * 交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
 *
 * 最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
 */
public class Q5247minimumSwap {
    public int minimumSwap(String s1, String s2) {
        int s1_x_num = 0;
        int s1_y_num = 0;
        for (int i = 0; i < s1.length(); i ++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x') {
                    s1_x_num ++;
                } else {
                    s1_y_num ++;
                }
            }
        }
        if ((s1_x_num & 1) != (s1_y_num & 1)) return -1;
        int x_sum = s1_x_num / 2;
        int y_sum = s1_y_num / 2;
        return s1_x_num % 2 == 0 ? x_sum + y_sum : x_sum + y_sum + 2;
    }

}
