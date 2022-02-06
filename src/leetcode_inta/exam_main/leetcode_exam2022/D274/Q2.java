package leetcode_inta.exam_main.leetcode_exam2022.D274;

/**
 * @author inta
 * @date 2022/1/2
 * @describe
 */
public class Q2 {
    // 行行之间累加乘积
    public int numberOfBeams(String[] bank) {
        // 统计行中1的数量
        int[] counts = new int[bank.length];
        for (int i = 0; i < bank.length; i++) {
            counts[i] = calcNum(bank[i]);
        }
        int res = 0;
        int pre = 0;
        for (int count : counts) {
            if (count > 0) {
                res += count * pre;
                pre = count;
            }
        }
        return res;
    }

    private int calcNum(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                res++;
            }
        }
        return res;
    }
}
