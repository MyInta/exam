package leetcode_inta.leetcode1301_1350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/5/23
 * @describe 给你一个大小为 m * n 的方阵 mat，方阵由若干军人和平民组成，分别用 1 和 0 表示。
 *
 * 请你返回方阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
 *
 * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 *
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * 输出：[2,0,3]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 2
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 2
 * 行 4 -> 5
 * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
 *
 * 示例 2：
 *
 * 输入：mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * 输出：[0,2]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 1
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 1
 * 从最弱到最强对这些行排序后得到 [0,2,3,1]
 *
 *
 *
 * 提示：
 *
 *     m == mat.length
 *     n == mat[i].length
 *     2 <= n, m <= 100
 *     1 <= k <= m
 *     matrix[i][j] 不是 0 就是 1
 *
 */
public class Q1337kWeakestRows {
    //题意简化，每行1的数量，按从小到大排序时，对应索引号，取其前k项，为解
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        //核心在于使用一个二维数组，保存原先索引和对应军官值
        int[][] arr = new int[m][2];
        for (int i = 0; i < m; i++) {
            arr[i][0] = i;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) arr[i][1] ++;
                else break;
            }
        }
        Arrays.sort(arr, (a, b) -> a[1] - b[1]);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            //将其原先的索引赋给res
            res[i] = arr[i][0];
        }
        return res;
    }

    //题解大佬给的思路：将军官数量乘一定值后加上索引，排序后，对该值取余即可
    public int[] kWeakestRows2(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[] counts = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) counts[i] ++;
            }
            //因为n最长100，扩大数量，使用101或者1000都行，怎么方便怎么来
            counts[i] = 101 * counts[i] + i;
        }
        Arrays.sort(counts);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = counts[i] % 101;
        }
        return res;
    }
}
