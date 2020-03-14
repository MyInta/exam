package leetcode_inta.leetcode251_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/9/11
 * @describe 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class Q279NumSquares {

    //解法：使用图论，求n-0的最短路径；
    // 如12 - >0 分别减去 1 4 9 16 ... (i^2) 遇到负值就返回进行下一轮，遇到减为0就找到正解，遇到非负非零就保存值
    // 先设置一个节点
    private class Node{
        //节点值
        int val;
        //节点存在步骤
        int step;
        Node(int val, int step) {
            this.val = val;
            this.step = step;
        }
    }
    public int numSquares(int n) {
        int num = n;
        //需要一个队列来保存值
        Queue<Node> queue = new LinkedList<>();
        Node first = new Node(num,0);
        queue.add(first);
        //添加一个boolean数组来去冗余
        boolean[] visited = new boolean[n + 1];
        while (!queue.isEmpty()) {
            int val = queue.peek().val;
            int step = queue.peek().step;
            queue.remove();

            for (int i = 1;; i++) {
                int a = val - i*i;
                if (a == 0) {
                    return step + 1;
                }
                if (a < 0) {
                    //如果a小于零，说明a不够减了，因子大于最大开方根，非我们所需
                    break;
                }
                //只有在未访问过的情况下添加新的节点信息
                if (!visited[a]) {
                    //如果不满足上诉两种，即a大于零
                    queue.add(new Node(a, step + 1));
                    visited[a] = true;
                }
            }
        }
        //照理是绝对有解的，但是为了满足编译器需求，我们增设-1为意外的返回值
        return -1;
    }
}
