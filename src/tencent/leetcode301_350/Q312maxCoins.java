package tencent.leetcode301_350;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/3
 * @describe 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，
 * 你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。
 * 注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */
public class Q312maxCoins {
    //动态规划 dp[i][j] 表示 i+1->j-1戳气球
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 2][len + 2];
        //创建一个新数组
        int[] newNums = new int[len + 2];
        //将数组扩充为一个新的数组前后分别设为1
        System.arraycopy(nums, 0, newNums, 1, len);
        newNums[0] = 1;
        newNums[len + 1] = 1;
        int newLen = newNums.length;
        //range 代表j - i的值，数组长度至少为2（上面加了两个元素），
        // 故从索引2开始(若空则因为初始化int[][]都为0)到数组索引最后的newLen - 1
        for (int range = 2; range < newLen; range ++) {
            //上面定义了长度，而i定义从何开始，[0,newLen)范围内，长度为range，所以是[0,newLen-range)
            for (int i = 0; i < newLen - range; i ++) {
                //定义末端，即i->j构成
                int j = i + range;
                //k是i-j中的一个值
                for (int k = i + 1; k < j; k ++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i]*newNums[k]*newNums[j]);
                }
            }
        }
        //根据定义，要找1->n 即dp[1-1][n+1] 其中n为原数组长度，即dp[0][len + 1]，等价于dp[0][newLen - 1]
        return dp[0][newLen - 1];
    }
}
