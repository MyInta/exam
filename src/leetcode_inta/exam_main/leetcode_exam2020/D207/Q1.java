package leetcode_inta.exam_main.leetcode_exam2020.D207;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/9/20
 * @describe
 */
public class Q1 {

    public String reorderSpaces(String text) {
        int sum_len = text.length();
        List<String> splits = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c != ' ') {
                sb.append(c);
            } else {
                if (sb.length() == 0) continue;
                splits.add(new String(sb));
                sb = new StringBuilder();
            }
        }
        if (sb.length() != 0) {
            splits.add(new String(sb));
            sb = new StringBuilder();
        }
        int words_len = 0;
        for (String str : splits) {
            words_len += str.length();
        }
        if (splits.size() == 1) {
            sb.append(splits.get(0));
            int end = sum_len - splits.get(0).length();
            for (int i = 0; i < end; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int resume = (sum_len - words_len) % (splits.size() - 1);
        int rang = (sum_len - words_len) / (splits.size() - 1);
        for (int k = 0; k < splits.size() - 1; k++) {
            sb.append(splits.get(k));
            for (int i = 0; i < rang; i++) {
                sb.append(" ");
            }
        }
        sb.append(splits.get(splits.size() - 1));
        for (int i = 0; i < resume; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
