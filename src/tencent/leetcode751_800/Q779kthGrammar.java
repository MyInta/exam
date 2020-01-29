package tencent.leetcode751_800;

/**
 * @author inta
 * @date 2020/1/29
 * @describe 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 *
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 *
 *
 * 例子:
 *
 * 输入: N = 1, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 2
 * 输出: 1
 *
 * 输入: N = 4, K = 5
 * 输出: 1
 *
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 *
 * 注意：
 *
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 *
 */
public class Q779kthGrammar {
    //找规律，第N行K的数是由第N-1行（K+1）/2而来
    public int kthGrammar(int N, int K) {
        if (N == 1) return 0;
        //这里是理解为一棵满二叉树，找上一层位置为0还是1 0的话，就是1 - k%2反之为k%2
        if (kthGrammar(N - 1, (K + 1) / 2) == 0) {
            return 1 - (K % 2);
        } else {
            return K % 2;
        }
    }
}
