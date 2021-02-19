package leetcode_inta.leetcode1001_1050;

/**
 * @author inta
 * @date 2021/2/19
 * @describe 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 */
public class Q1004longestOnes {
    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int temp = K;
        int res;
        while (right < A.length && temp > 0) {
            if (A[right] == 0) {
                temp--;
            }
            right++;
        }
        if (right == A.length) {
            return A.length;
        }
        res = right - left;
        while (right <= A.length) {
            while (left < A.length && A[left] == 1) {
                left++;
            }
            left++;
            while (right < A.length && A[right] == 1) {
                right++;
            }
            right++;
            while (right < A.length && A[right] == 1) {
                right++;
            }
            res = Math.max(right - left, res);
        }
        return res;
    }
}
