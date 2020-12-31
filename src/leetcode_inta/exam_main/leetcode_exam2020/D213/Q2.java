package leetcode_inta.exam_main.leetcode_exam2020.D213;

/**
 * @author inta
 * @date 2020/11/1
 * @describe
 */
public class Q2 {

    public int countVowelStrings(int n) {
        return solution(n, 5);
    }
    private int solution(int n, int all) {
        if (n == 1 || all == 1) return all;
        return solution(n, all - 1) + solution(n - 1, all);
    }
}
