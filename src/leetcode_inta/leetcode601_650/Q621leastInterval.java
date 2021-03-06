package leetcode_inta.leetcode601_650;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/10/15
 * @describe 给定一个用字符数组表示的 CPU 需要执行的任务列表。
 * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，
 * 并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 *
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 */
public class Q621leastInterval {

    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char task : tasks) {
            counts[task % 26] ++;
        }
        Arrays.sort(counts);
        int res = (counts[25] - 1) * (n + 1) + 1;
        int i = 25;
        while (i >= 1 && counts[i] == counts[i - 1]) {
            //当后一个元素数量长度和最大的值相等时候，就直接给结果扩一即可
            res ++;
            i --;
        }
        return Math.max(res, tasks.length);
    }
}
