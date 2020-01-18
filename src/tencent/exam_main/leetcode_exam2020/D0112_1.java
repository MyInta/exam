package tencent.exam_main.leetcode_exam2020;

/**
 * @author inta
 * @date 2020/1/12
 * @describe
 */
public class D0112_1 {
    public int[] getNoZeroIntegers(int n) {
        int[] res = new int[2];
        for (int i = 1; i < n; i ++) {
            if (!isValid(i) && !isValid(n - i)) {
                res[0] = i;
                res[1] = n - i;
                break;
            }
        }
        return res;
    }
    //判断是否含有零
    private boolean isValid(int n) {
        while (n > 0) {
            if (n % 10 == 0) return true;
            n /= 10;
        }
        return false;
    }
}
