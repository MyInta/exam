package leetcode_inta.exam_main.leetcode_exam2021.D267;

/**
 * @author inta
 * @date 2021/11/14
 * @describe
 */
public class Q1 {
    // 数据量比较小，可以直接暴力求解
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        int len = tickets.length;
        while (tickets[k] > 0) {
            for (int i = 0; i < len; i++) {
                if (i == k && tickets[i] == 1) {
                    return res + 1;
                }
                if (tickets[i] > 0) {
                    tickets[i]--;
                    res++;
                }
            }
        }
        return res;
    }
}
