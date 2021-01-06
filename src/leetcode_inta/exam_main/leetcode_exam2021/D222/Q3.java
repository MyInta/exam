package leetcode_inta.exam_main.leetcode_exam2021.D222;

/**
 * @author inta
 * @date 2021/1/3
 * @describe
 */
public class Q3 {
    // 移动，先看能不能形成left mid right 不行形成退出，可以就计算mid扩张数量，再扩张left，重复
    public int waysToSplit(int[] nums) {
        int res = 0;
        int mod = 1_000_000_007;
        // 空间换时间
        int[] counts = new int[nums.length];
        counts[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            counts[i] = counts[i - 1] + nums[i];
        }
        // 遍历left的情况
        for (int i = 0; counts[i] <= counts[nums.length - 1] / 3; i++) {
            // 使用二分找到mid的左边界和右边界，
            // 以形成counts[mid_left] - counts[left] >= counts[left]
            // 并且 counts[nums.length - 1] - counts[mid_right] >= counts[left]
            int midLeft = i + 1;
            int tempEnd = nums.length;
            while (midLeft < tempEnd) {
                int mid = midLeft + (tempEnd - midLeft) / 2;
                if (counts[mid] - counts[i] >= counts[i]) {
                    tempEnd = mid;
                } else {
                    midLeft = mid + 1;
                }
            }
            if (midLeft == nums.length) {
                break;
            }
            int midRight = midLeft;
            tempEnd = nums.length - 1;
            while (midRight < tempEnd) {
                int mid = midRight + (tempEnd - midRight) / 2;
                if (counts[nums.length - 1] - counts[mid] >= counts[mid] - counts[i]) {
                    midRight = mid + 1;
                } else {
                    tempEnd = mid;
                }
            }
            // 回归，因为上面找到的是正确值偏右一位
            midRight--;
            if (midRight >= midLeft) {
                res = (res + midRight - midLeft + 1) % mod;
            }
        }
        return res % mod;
    }
}
