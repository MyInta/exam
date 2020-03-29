package leetcode_inta.exam_main.leetcode_exam2020.D182;

/**
 * @author inta
 * @date 2020/3/29
 * @describe
 */
public class Q2 {
    //暴力
    public int numTeams(int[] rating) {
        int n = rating.length;
        if (n < 3) return 0;
        int res = 0;
        int first = 0, second, third;
        while (first < n - 2) {
            second = first + 1;
            while (second < n - 1) {
                third = second + 1;
                while (third < n) {
                    if (rating[first] < rating[second] && rating[second] < rating[third]
                            || rating[first] > rating[second] && rating[second] > rating[third]) res ++;
                    third ++;
                }
                second ++;
            }
            first ++;
        }
        return res;
    }
}
