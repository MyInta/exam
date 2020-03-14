package leetcode_inta.leetcode1001_1050;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/11/1
 * @describe 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。
 * 假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 */
public class Q1046lastStoneWeight {
    //优先队列执行速度低下
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) return 0;
        //使用lambda表达式反而效率会低，不知道为什么 = new PriorityQueue<>((a,b) -> b-a);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int stone : stones) {
            priorityQueue.add(stone);
        }
        while (priorityQueue.size() > 1) {
            int y = priorityQueue.poll();
            int x = priorityQueue.poll();
            y -= x;
            if (y != 0) priorityQueue.add(y);
        }
        return priorityQueue.isEmpty() ? 0 : priorityQueue.peek();
    }
    //反而是贪心算法时间复杂度低
    public int lastStoneWeight2(int[] stones) {
        int weight;
        if (stones == null || stones.length == 0) return 0;
        for (int i = 0; i < stones.length - 1; i ++) {
            Arrays.sort(stones);
            weight = stones[stones.length - 1] - stones[stones.length - 2];
            stones[stones.length - 2] = 0;
            stones[stones.length - 1] = weight;
        }
        return stones[stones.length - 1];
    }
}
