package leetcode_inta.exam_main.leetcode_exam2020.D213;

/**
 * @author inta
 * @date 2020/11/1
 * @describe
 */
public class Q1 {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int counts = arr.length;
        int count = 0;
        for (int[] p : pieces) {
            int index = -1;
            for (int i = 0; i < p.length; i++) {
                boolean find = false;
                for (int j = index + 1; j < counts; j++) {
                    if (arr[j] == p[i]) {
                        index = j;
                        find = true;
                        break;
                    }
                }
                if (!find) return false;
            }
            count += p.length;
        }
        return count == counts;
    }
}
