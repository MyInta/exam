package leetcode_inta.exam_main.leetcode_exam2020.D193;

/**
 * @author inta
 * @date 2020/6/14
 * @describe
 */
public class Q1 {
    public int[] runningSum(int[] nums) {
        int[] pre = new int[nums.length];
        pre[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        return pre;
    }
}
