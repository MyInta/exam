package leetcode_inta.leetcode201_250;

import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2019/10/20
 * @describe 编写一个算法来判断一个数是不是“快乐数”。
 *
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。
 * 如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例: 
 *
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 */
public class Q202isHappy {
    private Set<Integer> hs = new HashSet<>();
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        if (hs.contains(n)) {
            return false;
        }
        hs.add(n);
        String nStr = String.valueOf(n);
        int res = 0;
        for (int i = 0; i < nStr.length(); i ++) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return isHappy(res);
    }

    //使用快慢指针来做，就是解决一个闭环的问题
    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int fast = n;
        int slow = n;
        do {
            slow = squareN(slow);
            fast = squareN(squareN(fast));
        } while (slow != fast);
        if (slow == 1) return true;
        return false;
    }
    //返回n各位平方之和
    private int squareN(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}
