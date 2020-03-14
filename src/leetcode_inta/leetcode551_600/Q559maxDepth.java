package leetcode_inta.leetcode551_600;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/1/13
 * @describe 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *
 * 我们应返回其最大深度，3。
 *
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class Q559maxDepth {
    private class Node{
        int val;
        List<Node> children;
        private Node(){}
        Node(int val) {
            this.val = val;
        }
        private Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    //dfs
    private int res;
    public int maxDepth(Node root) {
        if (root == null) return 0;
        res = 0;
        dfs(root, 0);
        return res;
    }
    private void dfs(Node root, int dept) {
        if (root == null || root.children.isEmpty()) {
            res = Math.max(res, dept + 1);
            return;
        }
        for (Node node : root.children) {
            dfs(node, dept + 1);
        }
    }

    //bfs
    public int maxDepth2(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (Node node : root.children) {
            int depth = maxDepth2(node);
            max = Math.max(max, depth);
        }
        return max + 1;
    }

    //迭代
    public int maxDepth3(Node root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<Node> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            //深度加1
            depth ++;
            int size = stack.size();
            for (int i = 0; i < size; i ++) {
                Node node = stack.poll();
                if (!node.children.isEmpty()) {
                    for (Node n : node.children) {
                        stack.add(n);
                    }
                }
            }
        }
        return depth;
    }
}
