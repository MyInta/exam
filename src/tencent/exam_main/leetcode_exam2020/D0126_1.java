package tencent.exam_main.leetcode_exam2020;

/**
 * @author inta
 * @date 2020/1/26
 * @describe
 */
public class D0126_1 {
    public int removePalindromeSub(String s) {
        if (s == null || s.equals("")) return 0;
        String temp = s;
        int count = 1;
        if (!isValid(s)) {
            count ++;
        }
        return count;
    }
    private boolean isValid(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left ++;
            right --;
        }
        return true;
    }
}
