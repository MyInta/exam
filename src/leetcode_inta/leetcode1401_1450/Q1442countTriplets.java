package leetcode_inta.leetcode1401_1450;

/**
 * @author inta
 * @date 2021/5/18
 * @describe 给你一个整数数组 arr 。
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 示例 1：
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * 提示：
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class Q1442countTriplets {
    // 52ms 仅击败8%用户
    public int countTriplets(int[] arr) {
        int len = arr.length;
        // 使用數組保存好亦或情況
        int[][] dp = new int[len][len];
        dp[0][0] = arr[0];
        for (int j = 1; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = dp[i][j - 1] ^ arr[j];
            }
        }
        int res = 0;
        for (int k = 1; k < len; k++) {
            for (int j = 1; j <= k; j++) {
                for (int i = 0; i < j; i++) {
                    if (dp[i][j - 1] == dp[j][k]) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    // 看了评论区的思路，豁然开朗
    // 需要用到a^b=b^a ==> 如果a^b^c^...^n = 0那么左式中任意几个取出来亦或值和剩余元素的亦或值相等
    public int countTriplets2(int[] arr) {
        int len = arr.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum ^= arr[j];
                if (sum == 0 && i != j) {
                    // i到j区间亦或结果为0，那么任意取出几个元素亦或结果和剩余元素亦或结果相等，这种取法有j-i个
                    res += j - i;
                }
            }
        }
        return res;
    }
}
