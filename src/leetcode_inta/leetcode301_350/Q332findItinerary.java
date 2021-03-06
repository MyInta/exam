package leetcode_inta.leetcode301_350;

import java.util.*;

/**
 * @author inta
 * @date 2020/8/27
 * @describe
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 * 说明:
 *
 *     如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。
 *     例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 *     所有的机场都用三个大写字母表示（机场代码）。
 *     假定所有机票至少存在一种合理的行程。
 *
 * 示例 1:
 *
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * 示例 2:
 *
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 */
public class Q332findItinerary {
    //看了评论区域，是直接深入遍历记录的关联表，还需要再理解一下
    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<>();
        res = new LinkedList<>();
        //建立关联表
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(src)) map.put(src, new PriorityQueue<>());
            map.get(src).add(to);
        }
        //深入遍历
        dfs("JFK");
        return res;
    }
    private Map<String, PriorityQueue<String>> map;
    private List<String> res;
    private void dfs(String str) {
        PriorityQueue<String> pq = map.get(str);
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll());
        }
        //如果队列到头，那么就往回插入信息
        ((LinkedList<String>)res).addFirst(str);
    }
}
