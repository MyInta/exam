package leetcode_inta.leetcode101_150;

import java.util.List;

/**
 * @author inta
 * @date 2019/11/21
 * @describe 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */
public class Q120minimumTotal {
    //看题意，似乎是dfs 以及规律为 下一行的本身索引或+1,结果【TLE】，需要剪枝，但是又不想用额外空间。。。
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0, 0);
    }
    private int dfs(List<List<Integer>> triangle, int row, int index, int res) {
        //越界返回
        if (row == triangle.size() || index == triangle.get(row).size()) return res;
        int temp = triangle.get(row).get(index);
        res = Math.min(dfs(triangle, row + 1, index, res + temp), dfs(triangle, row + 1, index + 1, res + temp));
        return res;
    }

    //加个记忆存储
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int len = triangle.size();
        Integer[][] mem = new Integer[len][len];
        return mem_dfs(triangle, 0, 0, mem);
    }
    private int mem_dfs(List<List<Integer>> triangle, int row, int index, Integer[][] mem) {
        //如果遇到之前累加过的，直接返回累加值即可
        if (mem[row][index] != null) {
            return mem[row][index];
        }
        mem[row][index] = triangle.get(row).get(index);
        //遍历到最底层，返回各个元素,并且给记忆空间存好
        if (row == triangle.size() - 1) return mem[row][index];
        return mem[row][index] += Math.min(mem_dfs(triangle, row + 1, index, mem),
                mem_dfs(triangle, row + 1, index + 1, mem));
    }

    //也可以倒过来做,从开始末端更新，用dp记录列更新信息，将空间压缩到O(n)
    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int len = triangle.size();
        int[] dp = new int[len + 1];
        //遍历层
        for (int i = len - 1; i >= 0; i --) {
            //遍历每一层里的元素 根据规律，发现每一层数量和其层次相等，所以j也可以直接 <= i
            for (int j = 0; j < triangle.get(i).size(); j ++) {
                //每一层上升之后，浓缩为原有那一层的原位置索引以及后一位的最小值加上当前层的元素值
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
