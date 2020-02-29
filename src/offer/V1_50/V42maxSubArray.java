package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/29
 * @describe 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *  
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *  LC53
 */
public class V42maxSubArray {
    public int maxSubArray(int[] nums) {
        //初始一个窗口累加值为单个元素
        int sum = nums[0];
        //最大值为其sum值
        int max = sum;
        //挨个遍历
        for (int i = 1; i < nums.length; i ++) {
            if (sum < 0) {
                //如果目前sum为负，就算后面有大正，那为啥不直接用那个大正呢，所以舍弃该sum
                sum = nums[i];
            } else {
                //如果sum为正，在没被减到负之前，我们考虑后面会不会有很大的正值弥补途中的小负
                sum += nums[i];
            }
            //每次操作完的sum我们都拿来和历史上最大值比较
            max = Math.max(max, sum);
        }
        return max;
    }
}
