package leetcode_inta.leetcode101_150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/1
 * @describe 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 *
 * 示例：
 * 输入：
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":
 * [{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 *  
 *
 * 提示：
 *
 * 节点数介于 1 到 100 之间。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 必须将给定节点的拷贝作为对克隆图的引用返回。
 */
public class Q133cloneGraph {
    private class Node{
        int val;
        List<Node> neighbors;
        Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    private Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        if (node.neighbors != null) {
            copy.neighbors = new ArrayList<>();
            for (Node n : node.neighbors) {
                copy.neighbors.add(cloneGraph(n));
            }
        }
        return copy;
    }

}
