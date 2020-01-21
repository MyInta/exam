package tencent.exam_main.leetcode_exam2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/19
 * @describe
 */
public class D0119_2 {
    public List<String> printVertically(String s) {
        //找最长
        String[] sSplits = s.split(" ");
        int size = 0;
        for (String sSplit : sSplits) {
            if (sSplit.length() > size) size = sSplit.length();
        }

        List<List<Character>> lists = new ArrayList<>();
        //添加长度size的子集合
        int temp = size;
        while (temp > 0) {
            lists.add(new ArrayList<>());
            temp --;
        }
        //lists的索引
        int cur = 0;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c != ' ') {
                lists.get(cur).add(c);
                cur ++;
            } else {
                while (cur < size) {
                    lists.get(cur).add(' ');
                    cur ++;
                }
                cur = 0;
            }
        }
        List<String> res = new ArrayList<>();
        for (List<Character> list : lists) {
            StringBuilder sb = new StringBuilder();
            int i;
            for (i = list.size() - 1; i >= 0; i --) {
                if (list.get(i) != ' ') break;
            }
            for (int j = 0; j <= i; j ++) {
                sb.append(list.get(j));
            }
            res.add(sb.toString());
        }
        return res;
    }
}
