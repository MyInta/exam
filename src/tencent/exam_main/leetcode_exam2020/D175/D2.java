package tencent.exam_main.leetcode_exam2020.D175;

/**
 * @author inta
 * @date 2020/2/9
 * @describe
 */
public class D2 {
    public int minSteps(String s, String t) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char c : s.toCharArray()) {
            count1[c - 'a'] ++;
        }
        for (char c : t.toCharArray()) {
            count2[c - 'a'] ++;
        }
        int res = 0;
        for (int i = 0; i < 26; i ++) {
            if (count1[i] > count2[i]) res += count1[i] - count2[i];
        }
        return res;
    }
}
