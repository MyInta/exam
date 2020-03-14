package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2020/1/28
 * @describe 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例 1:
 *
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 * 示例 2:
 *
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 示例 3:
 *
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 *
 */
public class Q376wiggleMaxLength {
    //思考的确可以用一个dp保留每个位置的最长序列，然后返回dp[0]即可
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i --) {
            dp[i] = Math.max(dp[i + 1], solution(nums, i));
        }
        return dp[0];
    }
    private int solution(int[] nums, int start) {
        int temp = nums[start];
        int count = 1;
        //标记是升序还是逆序状态，默认逆序
        boolean asc = false;
        //考虑前期可能相等情况，一直遍历直到不等，从不等处开始考虑计数，并且把初始升降情况做个记录
        for (; start < nums.length - 1; start ++) {
            if (nums[start + 1] == nums[start]) continue;
            //记录升降情况
            asc = nums[start + 1] > nums[start];
            break;
        }

        for (int i = start; i < nums.length - 1; i ++) {
            if (asc && nums[i + 1] > temp) {
                asc = false;
                count ++;
            } else if (!asc && nums[i + 1] < temp) {
                asc = true;
                count ++;
            }
            temp = nums[i + 1];
        }
        return count;
    }


    //大佬的解法思路是真的牛逼，这道题目被他们理解为统计上下波段数
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int asc = 1;
        int desc = 1;
        for (int i = 0; i < nums.length - 1; i ++) {
            if (nums[i + 1] == nums[i]) continue;
            if (nums[i + 1] > nums[i]) {
                asc = desc + 1;
            } else {
                desc = asc + 1;
            }
        }
        return Math.max(asc, desc);
    }
}
