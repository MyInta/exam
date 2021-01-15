package leetcode_inta.leetcode901_950;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2021/1/15
 * @describe n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 *
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 *
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10^4
 * 不会有两块石头放在同一个坐标点上
 */
public class Q947removeStones {
    // 思想：既然是要移除最多数量，那么就保留重叠次数最多的那块石头
    // 每次移除一处，重叠次数大家都减一，最后剩下出现次数为1的石头
    // 后来想想这样复杂度太高了，因为需要几轮遍历进行增删，仔细一想，这个过程，不就是把石头归类为一个个圈子么
    // 于是理解为寻找石头共同的圈子，最后统计有多少个圈子即可
    public int removeStones(int[][] stones) {
        // 按照上面的思维，这道题就是并查集了，难点是寻找父节点的时候，i与j两个坐标如何指向
        int n = stones.length;
        parents = new int[20000];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        // 记录不重复的圈子数量
        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            merge(stone[0], stone[1] + 10000);
        }
        for (int[] stone : stones) {
            set.add(find(stone[0]));
        }
        return n - set.size();
    }

    private int[] parents;

    // 合并，将坐标统一到一个目的地
    private void merge(int stonex, int stoney) {
        int parentX = find(stonex);
        int parentY = find(stoney);
        if (parentX != parentY) {
            parents[parentX] = parentY;
        }
    }

    // 查找目的地终点，并路径压缩
    private int find(int stone) {
        int cur = stone;
        int child = stone;
        int temp;
        while (cur != parents[cur]) {
            cur = parents[cur];
        }
        while (child != cur) {
            temp = parents[child];
            parents[child] = cur;
            child = temp;
        }
        return cur;
    }
}
