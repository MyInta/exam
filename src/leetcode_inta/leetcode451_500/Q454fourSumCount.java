package leetcode_inta.leetcode451_500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/19
 * @describe 给定四个包含整数的数组列表 A , B , C , D ,
 * 计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 */
public class Q454fourSumCount {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        if (A == null || A.length == 0) return res;
        int n = A.length;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                int AB = A[i] + B[j];
                hm.put(AB, hm.getOrDefault(AB, 0) + 1);
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                int CD = C[i] + D[j];
                res += hm.getOrDefault(-CD, 0);
            }
        }
        return res;
    }
}
