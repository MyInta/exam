package tencent.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/22
 * @describe
 */
public class D1222_1 {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += countNum(num);
        }
        return count;
    }
    private int countNum(int num) {
        int n = 0;
        while (num > 0) {
            n ++;
            num /= 10;
        }
        return n % 2 == 0 ? 1 : 0;
    }
}
