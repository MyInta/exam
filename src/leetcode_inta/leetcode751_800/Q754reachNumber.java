package leetcode_inta.leetcode751_800;

/**
 * @author inta
 * @date 2021/5/6
 * @describe 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
 * 返回到达终点需要的最小移动次数。
 * 示例 1:
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 * 示例 2:
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 * 注意:
 * target是在[-10^9, 10^9]范围中的非零整数。
 */
public class Q754reachNumber {
    // 规律题，累加余数为偶数，则数量不变，奇数则判断最后值奇偶数量加1或2
    public int reachNumber(int target) {
        int temp = Math.abs(target);
        int sum = 0;
        int count = 0;
        while (sum < temp) {
            count++;
            sum += count;
        }
        if ((sum - temp) % 2 == 0) {
            return count;
        }
        return count % 2 == 0 ? count + 1 : count + 2;
    }
}
