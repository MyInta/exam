package leetcode_inta.leetcode851_900;

/**
 * @author inta
 * @date 2021/2/1
 * @describe 爱丽丝和鲍勃有不同大小的糖果棒：
 * A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * 示例 1：
 *
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * 示例 2：
 *
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：A = [2], B = [1,3]
 * 输出：[2,3]
 * 示例 4：
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 * 提示：
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 */
public class Q888fairCandySwap {
    // 思路：分别求的A、B的总和sumA sumB，找出B[j]-A[i]==(sumB-sumA) / 2即为解
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] res = new int[2];
        int sumAlice = 0;
        int sumBob = 0;
        for (int a : A) {
            sumAlice += a;
        }
        for (int b : B) {
            sumBob += b;
        }
        // 题意讲答案存在，故不担心总和为奇数情况，且总和值在int范围内，不会溢出
        int dif = (sumAlice + sumBob - 2 * sumAlice) / 2;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (dif == B[j] - A[i]) {
                    res[0] = A[i];
                    res[1] = B[j];
                    return res;
                }
            }
        }
        return res;
    }

    // 使用set或数组来保存一方的值,我习惯对确定范围的参数使用数组保存，最终执行4ms，超越95%
    public int[] fairCandySwap2(int[] A, int[] B) {
        int[] res = new int[2];
        int[] counts = new int[100_001];
        int sumAlice = 0;
        int sumBob = 0;
        for (int a : A) {
            sumAlice += a;
        }
        for (int b : B) {
            sumBob += b;
            counts[b]++;
        }
        // 题意讲答案存在，故不担心总和为奇数情况，且总和值在int范围内，不会溢出
        int dif = (sumAlice + sumBob - 2 * sumAlice) / 2;

        for (int i = 0; i < A.length; i++) {
            int targetB = A[i] + dif;
            if (targetB < 0 || targetB >= counts.length) {
                continue;
            }
            if (counts[targetB] > 0) {
                res[0] = A[i];
                res[1] = targetB;
                return res;
            }
        }
        return res;
    }
}
