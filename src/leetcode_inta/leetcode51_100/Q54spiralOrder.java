package leetcode_inta.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/28
 * @describe 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 *
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */
public class Q54spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        //记录行m 写成down是用于边界判定
        int down = matrix.length;
        if (down == 0) return res;
        //记录列n
        int right = matrix[0].length;
        int left = 0;
        int up = 0;
        while (true) {
            //北侧 左到右
            for (int i = left; i < right; i++) {
                res.add(matrix[up][i]);
            }
            if (++up == down) break;
            //东侧 上到下
            for (int i = up; i < down; i++) {
                res.add(matrix[i][right - 1]);
            }
            if (--right == left) break;
            //南侧 右到左
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[down - 1][i]);
            }
            if (--down == up) break;
            //西侧 下到上
            for (int i = down - 1; i >= up; i--) {
                res.add(matrix[i][left]);
            }
            if (++left == right) break;
        }
        return res;
    }

    public static void main(String[] args) {
        Q54spiralOrder q = new Q54spiralOrder();
        int[][] m = {
                {1,2,3,4,5},
                {16,17,18,19,6},
                {15,24,25,20,7},
                {14,23,22,21,8},
                {13,12,11,10,9}
        };
        int[][] m2 = {
                {1,2,3,4,5},
                {12,13,14,15,6},
                {11,10,9,8,7}
        };
        int[][] m3 = {
                {1,2,3},
                {12,13,4},
                {11,14,5},
                {10,15,6},
                {9,8,7}
        };
        int[][] m4 = {
                {1,2,3}
        };
        int[][] m5 = {
                {7},
                {8},
                {9}
        };
        int[][] m6 = {
        };
        int[][] m7 = {
                {1,2,3},
                {6,5,4}
        };
        int[][] m8 = {
                {1,2,3,4,5,6},
                {18,19,20,21,22,7},
                {17,28,29,30,23,8},
                {16,27,26,25,24,9},
                {15,14,13,12,11,10}


//                {1,2,3,4,5,6},
//                {20,21,22,23,24,7},
//                {19,32,33,34,25,8},
//                {18,31,36,35,26,9},
//                {17,30,29,28,27,10},
//                {16,15,14,13,12,11}
        };
        int[][] m9 = {
                {1,2,3,4,5,6,7},
                {24,25,26,27,28,29,8},
                {23,40,41,42,43,30,9},
                {22,39,48,49,44,31,10},
                {21,38,47,46,45,32,11},
                {20,37,36,35,34,33,12},
                {19,18,17,16,15,14,13}
        };
        int[][] m10 = {
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };
        List<Integer> res = q.spiralOrder(m);
        List<Integer> res2 = q.spiralOrder(m2);
        List<Integer> res3 = q.spiralOrder(m3);
        List<Integer> res4 = q.spiralOrder(m4);
        List<Integer> res5 = q.spiralOrder(m5);
        List<Integer> res6 = q.spiralOrder(m6);
        List<Integer> res7 = q.spiralOrder(m7);
        List<Integer> res8 = q.spiralOrder(m8);
        List<Integer> res9 = q.spiralOrder(m9);
        List<Integer> res10 = q.spiralOrder(m10);
        System.out.println(res.toString());
        System.out.println(res2.toString());
        System.out.println(res3.toString());
        System.out.println(res4.toString());
        System.out.println(res5.toString());
        System.out.println(res6.toString());
        System.out.println(res7.toString());
        System.out.println(res8.toString());
        System.out.println(res9.toString());
        System.out.println(res10.toString());
    }
}
