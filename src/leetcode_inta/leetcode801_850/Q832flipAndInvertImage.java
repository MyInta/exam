package leetcode_inta.leetcode801_850;

/**
 * @author inta
 * @date 2019/11/26
 * @describe 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 *
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 *
 * 示例 1:
 *
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2:
 *
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 说明:
 *
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 */
public class Q832flipAndInvertImage {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < (m >> 1); j ++) {
                if (A[i][j] == A[i][m - j - 1]) {
                    if (A[i][j] == 1) {
                        A[i][j] = 0;
                        A[i][m - j - 1] = 0;
                    } else {
                        A[i][j] = 1;
                        A[i][m - j - 1] = 1;
                    }
                }
            }
            //遇到奇数情况中间值更改
            if (m % 2 == 1) {
                int index = m / 2;
                if (A[i][index] == 1) {
                    A[i][index] = 0;
                } else {
                    A[i][index] = 1;
                }
            }
        }
        return A;
    }

    public int[][] flipAndInvertImage2(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = A[i][j] ^ 1;
                A[i][j] = A[i][n - 1 - j] ^ 1;
                A[i][n - 1 - j] = temp;
            }
            if ((n & 1) == 1) {
                A[i][n / 2] ^= 1;
            }
        }
        return A;
    }

    public int[][] flipAndInvertImage3(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (A[i][j] == A[i][n - 1 - j]) {
                    A[i][j] ^= 1;
                    A[i][n - 1 - j] ^= 1;
                }
            }
            if ((n & 1) == 1) {
                A[i][n / 2] ^= 1;
            }
        }
        return A;
    }
}
