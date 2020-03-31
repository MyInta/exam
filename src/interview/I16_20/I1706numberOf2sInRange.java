package interview.I16_20;

/**
 * @author inta
 * @date 2020/3/31
 * @describe 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 *
 * 示例:
 *
 * 输入: 25
 * 输出: 9
 * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
 *
 * 提示：
 *
 *     n <= 10^9
 *
 */
public class I1706numberOf2sInRange {
    public int numberOf2sInRange(int n) {
        int ans = 0;
        for (long i = 1; i <= n; i*= 10) {
            int temp = (int)(n / i);
            int cur = temp % 10;
            int temp1 = temp / 10;
            int temp2 = (int)(n % i);
            ans += (int)(temp1 * i);
            if (cur == 2) ans += temp2 + 1;
            if (cur > 2) ans += i;
        }
        return ans;
    }

    //TODO 大佬们用的是数位dp做的，数位dp我还没学过
}
