package leetcode_inta.exam_main.leetcode_exam2020.D197;

/**
 * @author inta
 * @date 2020/7/12
 * @describe
 */
public class Q1 {
    public int numIdenticalPairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) res ++;
            }
        }
        return res;
    }
}
