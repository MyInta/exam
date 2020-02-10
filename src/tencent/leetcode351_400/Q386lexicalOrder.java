package tencent.leetcode351_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/10
 * @describe 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 *
 * 例如，
 *
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 *
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 *
 */
public class Q386lexicalOrder {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        String[] ns = new String[n + 1];
        ns[0] = "0";
        for (int i = 1; i <= n; i ++) {
            ns[i] = i + "";
        }
        Arrays.sort(ns);
        for (int j = 1; j <= n; j ++) {
            res.add(Integer.valueOf(ns[j]));
        }
        return res;
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i ++) {
            dfs(res, i, n);
        }
        return res;
    }
    private void dfs(List<Integer> list, int target, int n) {
        if (target > n) return;
        list.add(target);
        target *= 10;
        for (int i = 0; i < 10; i ++) {
            dfs(list, target + i, n);
        }
    }
}
