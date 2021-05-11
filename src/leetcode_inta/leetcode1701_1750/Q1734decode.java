package leetcode_inta.leetcode1701_1750;

/**
 * @author inta
 * @date 2021/5/11
 * @describe 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。
 * 比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * 示例 1：
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 * 提示：
 * 3 <= n < 10^5
 * n 是奇数。
 * encoded.length == n - 1
 */
public class Q1734decode {
    // 题目表述不清晰，原数组是1-n构成的
    public int[] decode(int[] encoded) {
        // 可以得到1->n的亦或结果，因为是奇数，在encode中的到的两两亦或可以取代1->n亦或式子，得到单独的解
        int allSum = 0;
        for (int i = 1; i <= encoded.length + 1; i++) {
            allSum ^= i;
        }
        // 得到1->n的亦或结果，间隔一位取encoded后几个元素即2^3 4^5 6^7 ... ... (n-1) ^ n
        for (int j = 1; j < encoded.length; j += 2) {
            allSum ^= encoded[j];
        }
        // 这样allSum就是两两消减后剩余的首位1，注意这里的1->n指的是数组中第几个元素
        // 得到首位元素和整个encoded,就和之前的简单题1486一样了
        int[] res = new int[encoded.length + 1];
        int index = 0;
        res[index] = allSum;
        while (index < encoded.length) {
            res[index + 1] = res[index] ^ encoded[index];
            index++;
        }
        return res;
    }
}
