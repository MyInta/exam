package leetcode_inta.exam_main.leetcode_exam2020.D220;

/**
 * @author inta
 * @date 2020/12/20
 * @describe
 */
public class Q2 {

    //删除不重复元素且合最大的一个连续子数组
    public int maximumUniqueSubarray(int[] nums) {
        //首先想到滑动窗口
        int[] counts = new int[10_001];
        int max = 0, left = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (counts[num] == 0) {
                counts[num] ++;
            } else {
                int temp = 0;
                for (int k = left; k < i; k++) {
                    temp += nums[k];
                }
                max = Math.max(max, temp);
                //并且之前元素得清除
                while (nums[left] != num) {
                    counts[nums[left]]--;
                    left++;
                }
                left++;
            }
        }
        //最后考虑可能后半段不重复情况
        int temp = 0;
        for (int k = left; k < nums.length; k++) {
            temp += nums[k];
        }
        max = Math.max(max, temp);
        return max;
    }
}
