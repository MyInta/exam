package leetcode_inta.exam_main.leetcode_exam2020.D199;

/**
 * @author inta
 * @date 2020/7/26
 * @describe
 */
public class Q1 {
    public String restoreString(String s, int[] indices) {
        char[] chars = new char[s.length()];
        int index = 0;
        for (int indice : indices) {
            chars[indice] = s.charAt(index ++);
        }
        return new String(chars);
    }
}
