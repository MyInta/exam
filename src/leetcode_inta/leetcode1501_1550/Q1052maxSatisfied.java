package leetcode_inta.leetcode1501_1550;

/**
 * @author inta
 * @date 2021/2/23
 * @describe 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，
 * 所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * 提示：
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class Q1052maxSatisfied {
    // X窗口内，顾客最大值
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        for (int i = 0; i < customers.length; i++) {
            res += grumpy[i] == 0 ? customers[i] : 0;
        }
        int left = 0;
        int right = 0;
        // 找到X范围内店老板生气时气跑的客人数量
        int curSum = 0;
        int tempX = X;
        while (tempX > 0) {
            curSum += grumpy[right] == 0 ? 0 : customers[right];
            tempX--;
            right++;
        }

        if (X >= customers.length) {
            return res + curSum;
        }

        // 滑窗找X范围内，可以气跑的客人数量最大值
        int curMax = curSum;
        while (right < customers.length) {
            curSum += grumpy[right] == 0 ? 0 : customers[right];
            curSum -= grumpy[left] == 0 ? 0 : customers[left];
            curMax = Math.max(curSum, curMax);
            left++;
            right++;
        }
        return res + curMax;
    }

    // 评论区的简洁版，简化了grumpy的01判断，以及指针变量数
    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int curSum = 0;
        for (int i = 0; i < X; i++) {
            curSum += customers[i] * grumpy[i];
        }

        int res = curSum;
        for (int i = X; i < customers.length; i++) {
            curSum += customers[i] * grumpy[i] - customers[i - X] * grumpy[i - X];
            res = Math.max(res, curSum);
        }

        // 此时已获取到了整个customers区间内在连续X分钟可能被气跑的顾客最大数量res
        for (int j = 0; j < customers.length; j++) {
            res += customers[j] * (grumpy[j] ^ 1);
        }
        return res;
    }
}
