package leetcode_inta.exam_main.leetcode_exam2020.D179;

/**
 * @author inta
 * @date 2020/3/8
 * @describe
 */
public class Q1 {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if ((n & 1) == 1) {
            while (n > 0) {
                sb.append('a');
                n --;
            }
        } else {
            sb.append('a');
            n --;
            while (n > 0) {
                sb.append('b');
                n --;
            }
        }
        return sb.toString();
    }
}
