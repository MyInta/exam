package leetcode_inta.exam_main.leetcode_exam2020.D192;

/**
 * @author inta
 * @date 2020/6/7
 * @describe
 */
public class Q1 {
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2*n];
        int index = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res[i] = nums[index ++];
        }
        for (int j = 1; j < nums.length; j += 2) {
            res[j] = nums[index ++];
        }
        return res;
    }
}
