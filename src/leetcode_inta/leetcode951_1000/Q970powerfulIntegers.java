package leetcode_inta.leetcode951_1000;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2019/11/11
 * @describe 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，
 * 其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
 * 返回值小于或等于 bound 的所有强整数组成的列表。
 * 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。
 *
 * 示例 1：
 *
 * 输入：x = 2, y = 3, bound = 10
 * 输出：[2,3,4,5,7,9,10]
 * 解释：
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 * 示例 2：
 *
 * 输入：x = 3, y = 5, bound = 15
 * 输出：[2,4,6,8,10,14]
 *  
 * 提示：
 *
 * 1 <= x <= 100
 * 1 <= y <= 100
 * 0 <= bound <= 10^6
 */
public class Q970powerfulIntegers {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        //保存结果（优化的话，可以省去这个，直接使用set作结果集，我这边是为了方便理解）
        List<Integer> res = new ArrayList<>();
        //判断值是否出现过了，去重
        Set<Integer> hs = new HashSet<>();
        int x_temp = bound;
        int y_temp = bound;
        //x取多少次幂极限逼近bound
        int x_max = 0;
        //y同上
        int y_max = 0;

        //边界值 去掉
        if (x == 1 && y == 1) {
            if (bound >= 2) res.add(2);
            return res;
        } else if (x == 1) {
            //记录y的幂次
            int j = 0;
            int sum = (int) (1 + Math.pow(y, j));
            while (sum <= bound) {
                res.add(sum);
                //每次取完值将幂次扩大
                j ++;
                sum = (int) (1 + Math.pow(y, j));
            }
            return res;
        } else if (y == 1) {
            //同上，只是y换x
            int i = 0;
            int sum = (int) (1 + Math.pow(x, i));
            while (sum <= bound) {
                res.add(sum);
                i ++;
                sum = (int) (1 + Math.pow(x, i));
            }
            return res;
        }

        //这一步，是为了求出当x非1情况下，它所能逼近bound的最大幂次
        while (x_temp != 0 && x != 1) {
            x_max ++;
            x_temp = x_temp / x;
        }
        //同上，求y的最大幂次
        while (y_temp != 0 && y != 1) {
            y_max ++;
            y_temp = y_temp / y;
        }

        //常规做法，挨个遍历，范围已经被缩小，不会TLE
        for (int i = 0; i <= x_max; i ++) {
            for (int j = 0; j <= y_max; j ++) {
                int sum = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (sum > bound) {
                    break;
                }
                if (!hs.contains(sum)) {
                    hs.add(sum);
                    res.add(sum);
                }
            }
        }
        return res;
    }
}
