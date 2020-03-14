package leetcode_inta.leetcode201_250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/9/19
 * @describe 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class Q216CombinationSum3 {
    private List<List<Integer>> res;
    private int sum = 0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        solution(k, 1, n, new ArrayList<>());
        return res;
    }
    private void solution(int k, int st, int n, ArrayList<Integer> arrayList) {
        if (sum > n ||k < 0) {
            return;
        }
        if (sum == n && k == 0) {
            res.add(new ArrayList<>(arrayList));
            return;
        }
        //发现运算效果没怎么大的变化，故还是用上面那个容易理解的吧
//        if (k >= 1 && n-sum < st*k + (k*k-k)>>1 || k < 0) {
//            return;
//        }
        for (int i = st; i <= 9; i++) {
            k--;
            arrayList.add(i);
            sum += i;
            solution(k, i+1, n, arrayList);
            arrayList.remove(arrayList.size()-1);
            sum -= i;
            k++;
        }
    }


/*    private List<List<Integer>> res;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        solution(k, 1, n, 0, new ArrayList<>());
        return res;
    }
    private void solution(int k, int st, int n, int sum, ArrayList<Integer> arrayList) {
        if (sum == n && k == 0) {
            res.add(new ArrayList<>(arrayList));
            return;
        }
        for (int i = st; i <= 9&&sum + i <= n&& k - 1 >= 0; i++) {
            arrayList.add(i);
            solution(k - 1, i+1, n, sum + i, arrayList);
            arrayList.remove(arrayList.size()-1);
        }
    }*/
}
