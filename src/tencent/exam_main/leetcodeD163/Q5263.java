package tencent.exam_main.leetcodeD163;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/11/17
 * @describe
 */
public class Q5263 {
    //核心思路，将矩阵拆成数组
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> init = new ArrayList<>(n * m);
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                init.add(grid[i][j]);
            }
        }
        int cur = (n * m - (k % (n * m))) % (n * m);
        List<Integer> list;
        for (int i = 0; i < n; i ++) {
            list = new ArrayList<>();
            for (int j = 0; j < m; j ++) {
                list.add(init.get((cur + i * m + j) % (n * m)));
            }
            res.add(list);
        }
        return res;
    }
}
