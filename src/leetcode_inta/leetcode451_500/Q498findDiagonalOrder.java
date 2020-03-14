package leetcode_inta.leetcode451_500;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/1/6
 * @describe 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 * 示例:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 * 解释:
 *
 * 说明:
 *
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class Q498findDiagonalOrder {
    //简单思路想，用max(n,m)个list，根据i+j为常值，决定在哪个list中添加,效率很低哦
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int n = matrix.length;
        int m = matrix[0].length;
        List<List<Integer>> lists = new ArrayList<>(Math.max(n, m));
        //确认下，i+j为偶数时候，逆向添加，而奇数时候，正向添加
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                //y = ax + b中的b
                int b = i + j;
                if (lists.size() == b) lists.add(new ArrayList<>());
                if (b % 2 == 1) {
                    lists.get(b).add(matrix[i][j]);
                } else {
                    lists.get(b).add(0, matrix[i][j]);
                }
            }
        }
        int[] res = new int[n * m];
        int index = 0;
        for (List<Integer> l : lists) {
            for (int res_i : l) {
                res[index ++] = res_i;
            }
        }
        return res;
    }

    //优化下，其实根本核心的i+j为常值思想是一样的，只是优化中直接添加了
    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int n = matrix.length;
        int m = matrix[0].length;
        int[] res = new int[n * m];
        //需要标记横坐标r和纵坐标c
        int r = 0, c = 0;
        for (int i = 0; i < res.length; i ++) {
            res[i] = matrix[r][c];
            //考虑方向，i+j偶数时候是往右上角方向 而i+j奇数时候是往左下角方向
            if ((r + c) % 2 == 0) {
                //偶数情况下考虑问题
                if (c == m - 1) {
                    //当碰到右壁时候，要换下一行
                    r ++;
                } else if (r == 0) {
                    //可能是在首行上，而其下一个元素是需要往右列移动
                    c ++;
                } else {
                    //其他情况下就是往右上移动一个
                    r --;
                    c ++;
                }
            } else {
                //奇数情况下
                if (r == n - 1) {
                    //可能是在末行上，而其下一个元素是需要往右列移动
                    c ++;
                } else if (c == 0) {
                    //当碰到左壁时候，要换下一行
                    r ++;
                } else {
                    //其他情况下就是往左下移动一个
                    r ++;
                    c --;
                }
            }
        }
        return res;
    }
}
