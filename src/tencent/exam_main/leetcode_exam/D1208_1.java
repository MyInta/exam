package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/8
 * @describe
 */
public class D1208_1 {
    public int subtractProductAndSum(int n) {
        int plus = 1;
        int add = 0;
        while (n != 0) {
            int temp = n % 10;
            n /= 10;
            plus *= temp;
            add += temp;
        }
        return plus - add;
    }
}
