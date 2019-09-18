package tencent.leetcode51_100;

import tencent.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/18
 * @describe 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class Q77Combine {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (k == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        solution(1, n, k, list);
        return res;
    }
    private void solution(int start, int len, int num, List<Integer> list) {
        if (list.size() == num) {
            res.add(new ArrayList<>(list));
            return;
        }
        //这里有一个剪枝，就是利用我们要找的数如1-7里面找3个的时候，i不可能大于5
        //即i小于等于n-（接下来要找的数量）+1 如找了3后接着找两个时，继续的i不可能大于7-(2)+1即6，顶多等于6
        for (int i = start; /*i <= len*/i <= len - (num - list.size()) + 1; i++) {
            list.add(i);
            solution(i + 1, len, num, list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        Q77Combine q77Combine = new Q77Combine();
        int n = 4;
        int k = 2;
        List<List<Integer>> res = q77Combine.combine(n,k);
        for (List l:res) {
            System.out.print(l.toString());
        }
    }
}
