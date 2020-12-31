package leetcode_inta.leetcode951_1000;

import java.util.Arrays;

/**
 * @author inta
 * @date 2020/11/29
 * @describe 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 *
 * 如果不能形成任何面积不为零的三角形，返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[2,1,2]
 * 输出：5
 * 示例 2：
 *
 * 输入：[1,2,1]
 * 输出：0
 * 示例 3：
 *
 * 输入：[3,2,3,4]
 * 输出：10
 * 示例 4：
 *
 * 输入：[3,6,2,3]
 * 输出：8
 *  
 *
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 *
 */
public class Q976largestPerimeter {

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int cur = A.length - 1;
        int res = 0;
        while (cur > 1) {
            if (A[cur] < A[cur - 1] + A[cur - 2]) return A[cur] + A[cur - 1] + A[cur - 2];
            cur--;
        }
        return res;
    }
}
