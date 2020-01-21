package tencent.leetcode1301_1350;

/**
 * @author inta
 * @date 2020/1/21
 * @describe 给你一个以行程长度编码压缩的整数列表 nums 。
 *
 * 考虑每相邻两个元素 [a, b] = [nums[2*i], nums[2*i+1]] 
 * （其中 i >= 0 ），每一对都表示解压后有 a 个值为 b 的元素。
 *
 * 请你返回解压后的列表。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[2,4,4,4]
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 100
 * nums.length % 2 == 0
 * 1 <= nums[i] <= 100
 *
 */
public class Q1313decompressRLElist {
    public int[] decompressRLElist(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i += 2) {
            len += nums[i];
        }
        int[] res = new int[len];
        int cur = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int temp = nums[i + 1];
            int size = nums[i];
            while (size > 0) {
                res[cur ++] = temp;
                size --;
            }
        }
        return res;
    }
}
