package leetcode_inta.exam_main.leetcode_exam2022.D275;

import java.util.*;

/**
 * @author inta
 * @date 2022/1/9
 * @describe
 */
public class Q3 {
    // 找同母异分词，以及少一个字符的词有多少个
    public static int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> set = new HashSet<>();
        for (String start : startWords) {
            int cur = 0;
            for (char c : start.toCharArray()) {
                cur += (1 << (c - 'a'));
            }
            set.add(cur);
        }
        // 再判断由start开头，并且末尾字符不在start上的词汇
        int res = 0;
        for (String tar : targetWords) {
            int[] counts = new int[26];
            int cur = 0;
            for (char c : tar.toCharArray()) {
                cur += (1 << (c - 'a'));
                counts[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (counts[i] == 1) {
                    if (set.contains(cur - (1 << i))) {
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    // ["ant","act","tack"]
    //["tack","act","acti"]
    public static void main(String[] args) {
        String[] startWords = {"ant","act","tack"};
        String[] targetWords = {"tack","act","acti"};
        System.out.println(wordCount(startWords, targetWords));
    }
}
