package tencent.leetcode51_100;

import java.util.ArrayList;

/**
 * @author inta
 * @date 2019/9/28
 * @describe 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 */
public class Q59generateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int start = 1;
        int end = n;
        int temp = 0;
        while (start < end) {
            res[start - 1][start - 1] = temp+1;
            for (int i = start; i < end; i++) {
                res[start - 1][i] = res[start - 1][i - 1] +1;
            }
            for (int i = start; i < end; i++) {
                res[i][end-1] = res[i-1][end - 1] + 1;
            }
            for (int i = end-1; i > start - 1; i--) {
                res[end-1][i - 1] = res[end-1][i] + 1;
            }
            for (int i = end - 1; i > start; i--) {
                res[i - 1][start - 1] = res[i][start - 1] +1;
            }
            temp = res[start][start - 1];
            start++;
            end--;
        }
        if (start == end) {
            res[start-1][start-1] = n*n;
        }
        return res;
    }

    public static void main(String[] args) {
        Q59generateMatrix q = new Q59generateMatrix();
        int[][] res = q.generateMatrix(1);
        for (int[] i : res) {
            for (int j = 0; j < i.length; j++) {
                System.out.print(i[j]+" ");
            }
            System.out.println();
        }
    }
}
