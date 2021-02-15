package leetcode_inta.leetcode901_950;

/**
 * @author inta
 * @date 2021/2/9
 * @describe 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 返回 A 中好子数组的数目。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *
 * 提示：
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class Q992subarraysWithKDistinct {
    // 思路：从索引位置左往右统计到K个不同，使用指针从出发点往右遍历遇到数量减少时停止，移动距离为该段个数
    public int subarraysWithKDistinct(int[] A, int K) {
        int from = 0;
        int cur;
        int res = 0;
        int[] counts = new int[A.length + 1];
        int dif = 0;
        for (int i = 0; i < A.length; i++) {
            if (dif == K) {
                cur = from;
                for (; cur <= i; cur++) {
                    res++;
                    if (counts[A[cur]]-- == 1) {
                        break;
                    }
                }
                // 再加回去
                for (int j = from; j <= cur; j++) {
                    counts[A[j]]++;
                }
            }
            if (counts[A[i]]++ == 0) {
                dif++;
            }
            if (dif > K) {
                for (int j = from; j < i; j++) {
                    if (counts[A[j]]-- == 1) {
                        dif--;
                        from = j + 1;
                        break;
                    }
                }
            }
        }
        if (dif == K) {
            for (; from <= A.length; from++) {
                res++;
                if (counts[A[from]]-- == 1) {
                    break;
                }
            }
        }
        return res;
    }
}
