package tencent.leetcode1001_1050;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/2/15
 * @describe 给定一个整数数组 A，我们只能用以下方法修改该数组：
 * 我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 *
 * 以这种方式修改数组后，返回数组可能的最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [4,2,3], K = 1
 * 输出：5
 * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
 * 示例 2：
 *
 * 输入：A = [3,-1,0,2], K = 3
 * 输出：6
 * 解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
 * 示例 3：
 *
 * 输入：A = [2,-3,-1,5,-4], K = 2
 * 输出：13
 * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 1 <= K <= 10000
 * -100 <= A[i] <= 100
 *
 */
public class Q1005largestSumAfterKNegations {
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int i;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int a : A) {
            //在K次内，优先把负数变成正数
            if (K > 0 && a < 0) {
                K --;
                a = -a;
            }
            //同时记录最小值，用来最后处理
            if (a < min) min = a;
            res += a;
        }
        //考虑K有盈余
        if (K % 2 == 1) {
            //为奇数的时候,K有盈余情况，A元素不可能为负，因为和上诉条件矛盾,所以奇数时候，把最小值减去即可
            res -= 2 * min;
        }
        return res;
    }
}
