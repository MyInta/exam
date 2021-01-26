package leetcode_inta.leetcode1101_1150;

import java.util.*;

/**
 * @author inta
 * @date 2021/1/26
 * @describe 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 * 示例：
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class Q1128numEquivDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] dominoe : dominoes) {
            int first = Math.min(dominoe[0], dominoe[1]);
            int second = Math.max(dominoe[0], dominoe[1]);
            int value = first * 10 + second;
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value == 1) {
                continue;
            }
            res += value * (value - 1) / 2;
        }
        return res;
    }

    // 看评论区，发现大佬使用数组把我上面简化了
    public int numEquivDominoPairs2(int[][] dominoes) {
        int res = 0;
        int[] counts = new int[100];
        for (int[] domi : dominoes) {
            Arrays.sort(domi);
            // 因为1 <= dominoes[i][j] <= 9,可以把i,j转化为10*i+j形式，使用counts记录该位置已有多少数量，累加即可
            res += counts[domi[0] * 10 + domi[1]]++;
        }
        return res;
    }
}
