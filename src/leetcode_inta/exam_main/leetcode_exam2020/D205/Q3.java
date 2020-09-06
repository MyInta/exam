package leetcode_inta.exam_main.leetcode_exam2020.D205;

/**
 * @author inta
 * @date 2020/9/6
 * @describe
 */
public class Q3 {

    public int minCost(String s, int[] cost) {
        int res = 0;
        int start = 0, end = 0;
        char pre = '?';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                start = i - 1;
                while (i < cost.length && s.charAt(i) == pre) {
                    i ++;
                }
                end = i;
                res += minRang(start, end, cost);
                i --;
            }
            pre = s.charAt(i);
        }
        return res;
    }
    //在一段aaa中，只保留最大开销的值 [start,end)
    private int minRang(int start, int end, int[] cost) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += cost[i];
            if (cost[i] > res) res = cost[i];
        }
        return sum - res;
    }
}
