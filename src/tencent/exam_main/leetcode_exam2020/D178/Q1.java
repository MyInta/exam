package tencent.exam_main.leetcode_exam2020.D178;

/**
 * @author inta
 * @date 2020/3/1
 * @describe
 */
public class Q1 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i ++) {
            int count = 0;
            for (int num : nums) {
                if (num < nums[i]) count ++;
            }
            res[i] = count;
        }
        return res;
    }
}
