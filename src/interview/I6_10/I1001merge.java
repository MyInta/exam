package interview.I6_10;

/**
 * @author inta
 * @date 2020/3/3
 * @describe 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class I1001merge {
    //指针思想
    public void merge(int[] A, int m, int[] B, int n) {
        //题意说了A空间大，也就是m>n
        int a = m - 1, b = n - 1, index = m + n - 1;
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                A[index --] = B[b --];
                continue;
            }
            if (b < 0) {
                A[index --] = A[a --];
                continue;
            }
            if (A[a] > B[b]) {
                A[index --] = A[a --];
            } else {
                A[index --] = B[b --];
            }
        }
    }
}
