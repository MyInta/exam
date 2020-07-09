package leetcode_inta.exam_main.leetcode_exam2020.D194;

/**
 * @author inta
 * @date 2020/6/21
 * @describe
 */
public class Q1 {

    public int xorOperation(int n, int start) {
        int res = 0;
        for (int i = start; i < start + 2 * n; i += 2) {
            res ^= i;
        }
        return res;
    }
}
