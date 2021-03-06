package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/9/29
 * @describe 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
 * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class Q11maxArea {
    //暴力破解
    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 1; j < height.length; j++) {
                res = Math.max(res, (j - i)*Math.min(height[i], height[j]));
            }
        }
        return res;
    }

    //双指针法，为什么可行：两端边界取其中小的一边靠近另一个指针，才有可能增大容量。
    public int maxArea2(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            res = Math.max(res, (right - left)*Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                //考虑特殊情况，两边相等的时候，为什么移动任何一侧都可行？这里我移动的是右侧
                //因为想要有新的最大值，两侧中必定得有两个大于边界的值，来满足|n_i-n_j| * |h[n_i]-h[n_j]|>|i-j|*|h[i]-h[j]|
                right--;
            }
        }
        return res;
    }
}