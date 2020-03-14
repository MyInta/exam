package leetcode_inta.leetcode251_300;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/11/14
 * @describe 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class Q257binaryTreePaths {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //非递归 迭代 关键在于保存的字符串也要为栈或者队列也行
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        //需要用栈保存字符串，不然回溯很难
//        StringBuilder sb = new StringBuilder();
        Stack<StringBuilder> sb_stack = new Stack<>();
        sb_stack.add(new StringBuilder());
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            StringBuilder sb = sb_stack.pop();
            sb.append(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
                sb_stack.add(new StringBuilder(sb).append("->"));
            }
            if (cur.left != null) {
                stack.add(cur.left);
                sb_stack.add(new StringBuilder(sb).append("->"));
            }
            if (cur.left == null && cur.right == null) {
                res.add(sb.toString());
            }
        }
        return res;
    }
    //递归
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, new StringBuilder());
        return res;
    }
    private void dfs(TreeNode root, List<String> res, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
            if (root.left == null && root.right == null) {
                res.add(sb.toString());
            } else {
                dfs(root.left, res, new StringBuilder(sb).append("->"));
                dfs(root.right, res, new StringBuilder(sb).append("->"));
            }
        } else {
            return;
        }
    }

}
