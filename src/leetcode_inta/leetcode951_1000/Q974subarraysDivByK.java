package leetcode_inta.leetcode951_1000;

/**
 * @author inta
 * @date 2020/11/2
 * @describe 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 * 示例：
 *
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 *
 * 提示：
 *
 *     1 <= A.length <= 30000
 *     -10000 <= A[i] <= 10000
 *     2 <= K <= 10000
 *
 */
public class Q974subarraysDivByK {
    //看了网友提供的思路，但还是不明白如何证明其过程可靠
    public int subarraysDivByK(int[] A, int K) {
        //记录可以达到的余数数量
        int[] counts = new int[K];
        //默认当余数为0，数量已经有一个，如A{K}
        counts[0]++;
        //分别记录当前累加余数，以及最终可实现整除的数量
        int pre = 0, res = 0;
        for (int a : A) {
            pre = (pre + a) % K;
            //方便让余数在counts记录数据的索引范围内
            if (pre < 0) pre += K;
            //添加此余数之前存在的数量，并且经过该元素后，标记该位置数量加一
            res += counts[pre]++;
        }
        return res;
    }
}
