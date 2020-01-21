package tencent.exam_main.leetcode_exam2020;

/**
 * @author inta
 * @date 2020/1/19
 * @describe
 */
public class D0119_1 {
    public int maximum69Number (int num) {
        int res = 0;
        boolean changed = false;
        String numStr = num + "";
        for (int i = 0; i < numStr.length(); i ++) {
            if (!changed && numStr.charAt(i) == '6') {
                res = res * 10 + 9;
                changed = true;
            } else {
                res = res * 10 + (numStr.charAt(i) - '0');
            }
        }
        return res;
    }
}
