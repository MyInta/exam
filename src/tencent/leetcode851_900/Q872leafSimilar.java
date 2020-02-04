package tencent.leetcode851_900;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/31
 * @describe 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *
 * 提示：
 *
 * 给定的两颗树可能会有 1 到 100 个结点。
 *
 */
public class Q872leafSimilar {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //分别遍历找叶子节点
    private List<Integer> r1 = new ArrayList<>();
    private List<Integer> r2 = new ArrayList<>();
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        dfs(root1, r1);
        dfs(root2, r2);
        //官解用了直接equals比较
//        return r1.equals(r2);
        if (r1.size() != r2.size()) return false;
        for (int i = 0; i < r1.size(); i ++) {
            if (r1.get(i) != r2.get(i)) return false;
        }
        return true;
    }
    //递归
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left != null) dfs(root.left, list);
        if (root.right != null) dfs(root.right, list);
        if (root.left == null && root.right == null) list.add(root.val);
    }
    //迭代
    private void dfs2(TreeNode root, List<Integer> list) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.right != null) stack.push(temp.right);
            if (temp.left != null) stack.push(temp.left);
            if (temp.left == null && temp.right == null) list.add(temp.val);
        }
    }
}