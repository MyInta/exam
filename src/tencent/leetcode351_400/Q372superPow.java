package tencent.leetcode351_400;

/**
 * @author inta
 * @date 2020/2/18
 * @describe 你的任务是计算 a^b 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 示例 1:
 *
 * 输入: a = 2, b = [3]
 * 输出: 8
 * 示例 2:
 *
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 */
public class Q372superPow {
    public int superPow(int a, int[] b) {
        int res = 1;
        int mol = 1337;
        for (int i : b) {
           res = solution(res % mol, 10, mol) * solution(a % mol, i, mol);
        }
        return res % mol;
    }
    //对于a^b获取取模mol后的值
    private int solution(int a, int b, int mol) {
        int res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % mol;
            a = a * a % mol;
            b >>= 1;
        }
        return res;
    }

}
