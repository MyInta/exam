package leetcode_inta.leetcode1201_1250;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2021/1/11
 * @describe 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 *
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 */
public class Q1202smallestStringWithSwaps {
    // 思路：合并数组，然后在数组范围内排最小字典序
    // 实现：用的并查集，将元素归一到父类，并且添加值进优先队列以用作最小字典序源
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();

        // 创建n个帮派，一开始每个人都是自己的帮主
        this.parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // 来一场武林盛宴，合并所有江湖小派，整合出的效果，也就是我们期待的数组合并
        for (List<Integer> pair : pairs) {
            merge(pair.get(0), pair.get(1));
        }

        // 存储帮主的索引位置，并且把帮派内成员元素都交给 优先队列管理
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();

        // 将帮派所有成员拥有的字符串上交帮派统一管理
        for (int j = 0; j < n; j++) {
            PriorityQueue<Character> pq = map.getOrDefault(find(j), new PriorityQueue<>());
            pq.add(s.charAt(j));
            map.put(find(j), pq);
        }

        // 然后帮派提取出财物(字符串)形成最小字典
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < n; k++) {
            sb.append(map.get(find(k)).poll());
        }
        return sb.toString();
    }

    // 帮派目录
    private int[] parents;

    // 查找帮派之主
    private int find(int i) {
        int cur = i;
        int child = i;
        while (this.parents[cur] != cur) {
            cur = parents[cur];
        }
        // 此时找到帮主，将所有成员压缩统一归帮主管理
        while (child != cur) {
            parents[child] = cur;
            child = parents[child];
        }
        return cur;
    }

    // 火并两个帮派，帮派之主随便找个帮主来顶着
    private void merge(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);
        if (parentI != parentJ) {
            this.parents[parentI] = parentJ;
        }
    }
}
