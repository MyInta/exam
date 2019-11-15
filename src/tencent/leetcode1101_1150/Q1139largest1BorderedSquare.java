package tencent.leetcode1101_1150;

/**
 * @author inta
 * @date 2019/11/13
 * @describe 给你一个由若干 0 和 1 组成的二维网格 grid，
 * 请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 *
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 *
 */
public class Q1139largest1BorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        //记录可以达到要求的边长长度
        int res = 0;
        //分别是数组的长和宽 m n
        int m = grid.length;
        int n = grid[0].length;
        //常规的数组遍历
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {

                //关键点一： 范围的优化处理，边长的选取，为比较数组的长度与索引的较小值（大于会越界）
                int x = Math.min(n - j, m - i);
                //因为已经选取了一个当前索引下可以达到的最长长度，就在该长度范围内进行遍历，k为边长0-x的变量
                for (int k = 0; k < x; k ++) {
                    //先预判上边 和 左边的数值是否符合要求，如果连上边和左边都没有达到要求，就不需要判断右边和下边了
                    if (grid[i][j + k] == 1 && grid[i + k][j] == 1) {
                        //布尔值是假定右边和下边是符合要求的
                        boolean flag = true;
                        //我们当前的边长为k k在0-x的范围内，而我们需要判断的右边和下边的边长范围是在当前0-k的距离内
                        for (int g = 0; g <= k; g ++) {
                            //判断右边和下边是否符合要求，如果不符合要求，就把之前设定的布尔值更改为false
                            if (grid[i + g][j + k] != 1 || grid[i + k][j + g] != 1) {
                                //下和左边的长度不符合要求，所以要把flag设置为false，说明不构成正方形
                                flag = false;
                            }
                        }
                        //当只有布尔值为true，即四边都满足要求的情况下，我们可以选取长度作为结果
                        if (flag) {
                            //边长选取当前最大边长，k需要考虑为0的情况时，原有元素也为边长1，故+1
                            res = Math.max(res, k + 1);
                        }
                    } else {
                        //若当前正方形的上边和左边都没有满足要求，直接跳出边长x内的判断，进入下一个元素重复操作
                        break;
                    }
                }
            }
        }
        //我们返回的是边长的平方
        return res * res;
    }
}
