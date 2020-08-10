package leetcode_inta.exam_main.leetcode_exam2020.D201;

/**
 * @author inta
 * @date 2020/8/9
 * @describe
 */
public class Q1 {
    public String makeGood(String s) {
        int size = s.length();
        boolean changed = false;
        for (int i = 0; i < size - 1; i++) {
            if (Math.abs(s.charAt(i) - s.charAt(i + 1)) == 32) {
                s = s.substring(0, i) + s.substring(i + 2, size);
                changed = true;
                break;
            }
        }
        if (changed) s = makeGood(s);
        return s;
    }
}
