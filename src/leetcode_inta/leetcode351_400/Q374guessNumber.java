package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2020/6/2
 * @describe 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 *
 * -1 : 我的数字比较小[猜测值比较大]
 *  1 : 我的数字比较大[猜测值比较小]
 *  0 : 恭喜！你猜对了！
 *
 *
 *
 * 示例 :
 *
 * 输入: n = 10, pick = 6
 * 输出: 6
 */
public class Q374guessNumber {
    public int guessNumber(int n) {
        //既然已经有判断大小的方法，那么就二分获取真实值吧
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) < 0) {
                right = mid;
            } else if (guess(mid) > 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    //假设的一个方法，由系统提供，返回与真实值比较的结果，比真实值大返回-1，小返回1，相等返回0
    private int guess(int num) {
        return 0;
    }
}
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */