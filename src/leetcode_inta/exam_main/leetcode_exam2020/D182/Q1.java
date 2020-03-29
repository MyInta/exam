package leetcode_inta.exam_main.leetcode_exam2020.D182;

/**
 * @author inta
 * @date 2020/3/29
 * @describe
 */
public class Q1 {
    public int findLucky(int[] arr) {
        int res = -1;
        int[] counts = new int[501];
        for (int a : arr) {
            counts[a] ++;
        }
        for (int i = counts.length - 1; i > 0; i--) {
            if (counts[i] == i) return i;
        }
        return res;
    }
}
