package tencent.leetcode501_550;

/**
 * @author inta
 * @date 2019/11/13
 * @describe 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 *
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 示例：
 *
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *  
 *
 * 提示：
 *
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 *
 */
public class Q507checkPerfectNumber {
    //思路一：找到该元素的所有正因素，累加比较
    public boolean checkPerfectNumber(int num) {
        if (num <= 5 || (num & 1) == 1) return false;
        //因为不能算自身，所以我们先把与自身配对的正因素1给提前取出来
        int sum = 1;
        for (int i = 2; i < Math.sqrt(num); i ++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
                if (sum > num) return false;
            }
        }
        //考虑可能刚好平方根为正因素的情况
        int coinside = (int) Math.sqrt(num);
        if (coinside * coinside == num) {
            sum += coinside;
        }
        return sum == num;
    }
}
