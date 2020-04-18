package leetcode_inta.exam_main.personal_exam;

/**
 * @author inta
 * @date 2020/4/18
 * @describe
 */
public class Q1 {
    public int minCount(int[] coins) {
        int res = 0;
        for (int coin : coins) {
            res += coin % 2 == 0 ? coin / 2 : coin / 2 + 1;
        }
        return res;
    }
}
