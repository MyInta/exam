package leetcode_inta.exam_main.leetcode_exam2020.D185;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2020/4/19
 * @describe
 */
public class Q1 {
    public String reformat(String s) {
        List<Character> set_char = new ArrayList<>();
        List<Character> set_int = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                set_int.add(c);
            } else {
                set_char.add(c);
            }
        }
        int size1 = set_char.size();
        int size2 = set_int.size();
        if (Math.abs(size1 - size2) >= 2) return "";
        StringBuilder sb = new StringBuilder();
        List<Character> temp;
        if (size1 < size2) {
            temp = set_char;
            set_char = set_int;
            set_int = temp;
        }
        for (int i = 0; i < set_int.size(); i++) {
            sb.append(set_char.get(i)).append(set_int.get(i));
        }
        if (size1 != size2) sb.append(set_char.get(set_char.size() - 1));
        return sb.toString();
    }
}
