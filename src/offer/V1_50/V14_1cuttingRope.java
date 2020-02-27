package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/26
 * @describe 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？
 * 例如，
 * 当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 *  LC343
 */
public class V14_1cuttingRope {

    //数学归纳法，归纳后发现得拆分出可能的3的个数以及一个余数，余数不能为1
    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int res = 1;
        //统计n可以有多少个3
        int nums = n / 3;
        //并且记录其余数
        int resume = n % 3;
        if (resume == 1) {
            //如果余数为1，就要舍弃一个3加起来
            nums --;
            res *= 4;
        } else if (resume == 2){
            //若余数为2,直接乘了
            res *= resume;
        }
        //剩余的3的个数的累乘
        while (nums > 0) {
          res *= 3;
          nums --;
        }
        return res;
    }
}
