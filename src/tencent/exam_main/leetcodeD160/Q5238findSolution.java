package tencent.exam_main.leetcodeD160;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/27
 * @describe 找出给定方程的正整数解
 */
public class Q5238findSolution {
    private class CustomFunction{
        public int f(int x, int y) {
            return 0;
        }
    }
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp;
        for (int i = 1; i <= z; i ++) {
            for (int j = z; j >= 1; j --) {
                if (customfunction.f(i, j) == z) {
                    temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                } else if (customfunction.f(i, j) < z) {
                    break;
                }
            }
        }
        return res;
    }
}
