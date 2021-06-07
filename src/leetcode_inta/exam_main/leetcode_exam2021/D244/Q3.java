package leetcode_inta.exam_main.leetcode_exam2021.D244;

/**
 * @author inta
 * @date 2021/6/6
 * @describe
 */
public class Q3 {
    public int minFlips(String s) {
        int len = s.length();
        StringBuilder sba = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sba.append("01");
            sbb.append("10");
        }
        char[] sbas = sba.toString().toCharArray();
        char[] sbbs = sbb.toString().toCharArray();
        char[] ss = (s + s).toCharArray();
        int res = len;
        int counta = 0;
        int countb = 0;
        for (int i = 0; i < len * 2; i++) {
            if (sbas[i] != ss[i]) {
                counta++;
            }
            if (sbbs[i] != ss[i]) {
                countb++;
            }
            if (i >= len) {
                counta += sbas[i - len] == ss[i - len] ? 0 : -1;
                countb += sbbs[i - len] == ss[i - len] ? 0 : -1;
            }
            if (i >= len - 1) {
                res = Math.min(res, Math.min(counta, countb));
            }
        }
        return res;
    }
}
