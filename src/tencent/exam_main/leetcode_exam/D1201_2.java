package tencent.exam_main.leetcode_exam;

import jdk.nashorn.api.tree.ThrowTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/1
 * @describe
 */
public class D1201_2 {
//    private List<Integer> res = new ArrayList<>();
//    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
//        int n = tomatoSlices / 2;
//        boolean[][] visited = new boolean[n + 1][cheeseSlices];
//        if (tomatoSlices % 2 == 1) return res;
//        dfs(tomatoSlices, cheeseSlices, 0, 0, visited);
//        return res;
//    }
//    private void dfs(int t, int c, int x, int y, boolean[][] visited) {
//        if (t == 0 && c == 0 && res.size() == 0) {
//            res.add(x);
//            res.add(y);
//            return;
//        }
//        if (t < 0 || c < 0 || res.size() == 2 || visited[x][y]) {
//            return;
//        } else {
//            visited[x][y] = true;
//            dfs(t - 4, c - 1, x + 1, y, visited);
//            dfs(t - 2, c - 1, x, y + 1, visited);
//        }
//    }

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<>();
        if (tomatoSlices % 2 == 1) return res;
        int y = 2 * cheeseSlices - tomatoSlices / 2;
        int x = tomatoSlices / 2 - cheeseSlices;
        if (x < 0 || y < 0) return res;
        res.add(x);
        res.add(y);
        return res;
    }
}
