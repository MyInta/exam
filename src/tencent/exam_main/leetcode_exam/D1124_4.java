package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/11/24
 * @describe
 */
public class D1124_4 {
    private int mol = 1_000_000_007;
    public int numWays(int steps, int arrLen) {
        return (int) Math.pow(2, steps - 1) % mol;
    }
}
