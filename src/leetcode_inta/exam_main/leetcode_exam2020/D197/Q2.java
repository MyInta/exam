package leetcode_inta.exam_main.leetcode_exam2020.D197;

/**
 * @author inta
 * @date 2020/7/12
 * @describe
 */
public class Q2 {
    public int numSub(String s) {
        int mol = 1000_000_007;
        long res = 0;
        int left = 0, right = 0;
        while (left < s.length()) {
            while (left < s.length() && s.charAt(left) == '0') {
                left ++;
            }
            right = left;
            while (right < s.length() && s.charAt(right) == '1') {
                right ++;
            }
            long first = right - left;
            if (first % 2 == 0) {
                res = res + ((first / 2) * (first + 1) % mol);
            } else {
                res = res + (((first + 1) / 2 )* first % mol);
            }
            res = res % mol;
            left = right;
        }
        res = res % mol;
        return (int)res;
    }
}
