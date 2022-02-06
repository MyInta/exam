package leetcode_inta.exam_main.leetcode_exam2022.D275;

/**
 * @author inta
 * @date 2022/1/9
 * @describe
 */
public class Q2 {
    // 环形数组，要求将1交换到全部相邻所需最少步数
    public int minSwaps(int[] nums) {
        // 可以理解为找区域k，这个k是所有1的数量，统计k中0的数量
        int countOne = 0;
        for (int num : nums) {
            if (num == 1) {
                countOne++;
            }
        }
        // 找区域k
        int left = 0;
        int tempValue = 0;
        for (int i = left; i < countOne; i++) {
            if (nums[i] == 0) {
                tempValue++;
            }
        }
        int res = tempValue;
        left++;
        while (left < nums.length) {
            if (nums[left - 1] == 0) {
                tempValue--;
            }
            if (nums[(left + countOne - 1) % nums.length] == 0) {
                tempValue++;
            }
            res = Math.min(res, tempValue);
            left++;
        }
        return res;
    }
}
