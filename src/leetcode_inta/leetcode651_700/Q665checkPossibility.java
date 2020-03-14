package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2019/12/21
 * @describe 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 *
 * 示例 1:
 *
 * 输入: [4,2,3]
 * 输出: True
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: [4,2,1]
 * 输出: False
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * 说明:  n 的范围为 [1, 10,000]。
 *
 */
public class Q665checkPossibility {
    public boolean checkPossibility(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            if (i < nums.length - 1 && nums[i] > nums[i + 1]) {
                boolean flag1 = true;
                boolean flag2 = true;
                int temp = nums[i + 1];
                //将后者变大
                nums[i + 1] = nums[i];
                for (int j = i + 1; j < nums.length; j ++) {
                    if (j < nums.length - 1 && nums[j] > nums[j + 1]) {
                        flag1 = false;
                        break;
                    }
                }
                nums[i + 1] = temp;
                //将前者变小
                nums[i] = temp;
                for (int j = 0; j < nums.length; j ++) {
                    if (j < nums.length - 1 && nums[j] > nums[j + 1]) {
                        flag2 = false;
                        break;
                    }
                }
                return flag1 || flag2;
            }
        }
        return true;
    }

    //看了大神的操作，可以实现一次遍历即可
    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            if (nums[i] > nums[i + 1]) {
                //遇到大小不一的情况,变更次数加一
                count ++;
                //当次数大于1次的时候，已经失去题目要求，不需要往下遍历
                if (count > 1) break;
                //本质是对异常两个数据变大变小
                if (i - 1 >= 0 && nums[i - 1] > nums[i + 1]) {
                    //我们将右元素放大到中间元素大小,也是保证了左边都是升序最小化
                    nums[i + 1] = nums[i];
                } else {
                    //左元素不存在，这种情况我们把中间元素缩小到右元素大小即可，保证左边最小化
                    nums[i] = nums[i + 1];
                }
            }
        }
        return count < 2;
    }
}
