package leetcode_inta.exam_main.leetcode_exam2020.D205;

/**
 * @author inta
 * @date 2020/9/6
 * @describe
 */
public class Q1 {

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '?') {
                if (i > 0 && i < s.length() - 1) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[i - 1] && c != chars[i + 1]) {
                            chars[i] = c;
                            break;
                        }
                    }
                } else if (i > 0) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[i - 1]) {
                            chars[i] = c;
                            break;
                        }
                    }
                } else if (i < s.length() - 1) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != chars[i + 1]) {
                            chars[i] = c;
                            break;
                        }
                    }
                } else {
                    chars[i] = 'a';
                }
            }
        }
        return new String(chars);
    }
}
