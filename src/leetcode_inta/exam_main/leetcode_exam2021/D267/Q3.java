package leetcode_inta.exam_main.leetcode_exam2021.D267;

/**
 * @author inta
 * @date 2021/11/14
 * @describe
 */
public class Q3 {
    // 简单实现，得到行列之后直接拼接
    public String decodeCiphertext(String encodedText, int rows) {
        char[] chars = encodedText.toCharArray();
        int len = chars.length;
        int cols = len / rows;
        StringBuilder sb = new StringBuilder();
        int curCol = 0;
        while (curCol <= cols) {
            int tempCol = curCol;
            for (int i = 0; i < rows; i++,tempCol++) {
                int nextIndex = i * cols + tempCol;
                if (nextIndex >= len) {
                    break;
                }
                sb.append(chars[nextIndex]);
            }
            curCol++;
        }
        // 最后部分空格需要去除，暴力点筛选掉
        String res = sb.toString();
        int lastIndex = res.length();
        for (int i = lastIndex - 1; i >= 0; i--) {
            if (res.charAt(i) != ' ') {
                return res.substring(0, i + 1);
            }
        }
        return res;
    }
}
