package leetcode_inta.exam_main.leetcode_exam2020.D199;

/**
 * @author inta
 * @date 2020/7/26
 * @describe
 */
public class Q2 {
    public int minFlips(String target) {
        //判断0的数量
        int res = 0, cur = 0;
        while (cur < target.length()) {
            while (cur < target.length() && target.charAt(cur) == '0') {
                cur ++;
            }
            if (cur < target.length() && target.charAt(cur) == '1') {
                res ++;
            }
            while (cur < target.length() && target.charAt(cur) == '1') {
                cur ++;
            }
            if (cur < target.length() && target.charAt(cur) == '0') {
                res ++;
            }
        }
        return res;
    }
}
