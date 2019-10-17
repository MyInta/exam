package tencent.leetcode101_150;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/17
 * @describe 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 */
public class Q118generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(1);
        if (numRows == 0) return res;
        res.add(l1);
        if (numRows == 1) {
            return res;
        }
        res.add(l2);
        if (numRows == 2) {
            return res;
        }
        List<Integer> newList;
        for (int i = 2; i < numRows; i ++) {
            newList = new ArrayList<>();
            newList.add(1);
            for (int j = 1; j <= i - 1; j ++) {
                List<Integer> temp = res.get(i - 1);
                //相当于A(i,j) = A(i-1,j-1) + A(i-1,j)
                newList.add(temp.get(j - 1) + temp.get(j));
            }
            newList.add(1);
            res.add(new ArrayList<>(newList));
        }
        return res;
    }
}
