package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/10/4
 * @describe 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class Q102levelOrder {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    private class Pair{
        TreeNode treeNode;
        int depth;
        private Pair(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        int depth = 0;
        List<Integer> list;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            depth = pair.depth;
            TreeNode node = pair.treeNode;
            if (res.size() == depth) {
                list = new ArrayList<>();
                res.add(list);
            } else {
                list = res.get(depth);
            }
            list.add(node.val);
            if (node.left != null) {
                q.add(new Pair(node.left, depth + 1));
            }
            if (node.right != null) {
                q.add(new Pair(node.right, depth + 1));
            }
        }
        return res;
    }

    //如果使用递归
    private List<List<Integer>> res;
    public List<List<Integer>> levelOrder2(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) return res;
        solution(root, 0);
        return res;
    }
    private void solution(TreeNode root, int depth) {
        if (depth == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(depth).add(root.val);
        if (root.left != null) solution(root.left, depth + 1);
        if (root.right != null) solution(root.right, depth + 1);
    }

}
