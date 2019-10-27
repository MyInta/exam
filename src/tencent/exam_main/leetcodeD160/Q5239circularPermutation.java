package tencent.exam_main.leetcodeD160;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/27
 * @describe 循环码排列 --->用的是格雷码生成法
 */
public class Q5239circularPermutation {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new LinkedList<>();
        String[] codes = new String[1 << n];
        solution(codes, n);
        int mark = 0;
        for (int i = 0; i < codes.length; i ++) {
            if (Integer.valueOf(codes[i],2).equals(start)) {
                mark = i;
                break;
            }
        }
        for (int i = mark; i < codes.length; i ++) {
            res.add(Integer.valueOf(codes[i], 2));
        }
        for (int i = 0; i < mark; i ++) {
            res.add(Integer.valueOf(codes[i], 2));
        }
        return res;
    }
    private void solution(String[] codes, int n) {
       if (n == 1) {
           codes[0] = "0";
           codes[1] = "1";
       } else {
           solution(codes, n - 1);
           int len = (1 << n);
           int half = len >> 1;
           for (int i = len - 1, j = 0; i >= 0; i --) {
               if (i < half) {
                   codes[i] = "0" + codes[--j];
               } else {
                   codes[i] = "1" + codes[j ++];
               }
           }
       }
    }
}
