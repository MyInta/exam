package leetcode_inta.exam_main.leetcode_exam2020.D220;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/12/20
 * @describe
 */
public class Q1 {
    public String reformatNumber(String number) {
        //获取所有数字，再考虑电话号码
        List<Character> list = new ArrayList<>();
        for (char c : number.toCharArray()) {
            if (c != ' ' && c != '-') {
                list.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < list.size() - 4; i += 3) {
            sb.append(list.get(i)).append(list.get(i + 1)).append(list.get(i + 2)).append('-');
        }
        //剩下少于4个的单独考虑
        int resume = list.size() - i;
        if (resume == 4) {
            sb.append(list.get(i)).append(list.get(i + 1)).append('-').append(list.get(i + 2)).append(list.get(i + 3));
        } else if (resume == 3) {
            sb.append(list.get(i)).append(list.get(i + 1)).append(list.get(i + 2));
        } else if (resume == 2) {
            sb.append(list.get(i)).append(list.get(i + 1));
        }
        return sb.toString();
    }
}
