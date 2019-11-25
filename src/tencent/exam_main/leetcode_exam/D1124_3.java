package tencent.exam_main.leetcode_exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/24
 * @describe
 */
public class D1124_3 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        if (searchWord == null || searchWord.length() == 0) return res;
        List<String> l;
        for (int i = 1; i <= searchWord.length(); i ++) {
            String temp = searchWord.substring(0, i);
            l = new ArrayList<>();
            int count = 0;
            for (String s : products) {
                if (s.startsWith(temp)) {
                    l.add(s);
                    count ++;
                }
                if (count == 3) break;
            }
            res.add(l);
        }
        return res;
    }
}
