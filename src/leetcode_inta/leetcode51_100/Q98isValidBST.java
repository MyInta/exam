package leetcode_inta.leetcode51_100;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/14
 * @describe 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class Q98isValidBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //非递归做法
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        //中序遍历，然后看次序是否是升序
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        long pre = Long.MIN_VALUE;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (cur.val <= pre) return false;
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
    //使用递归解法
    private long pre = Long.MIN_VALUE;
    private boolean flag = true;
    public boolean isValidBST2(TreeNode root) {
        solution(root);
        return flag;
    }
    private void solution(TreeNode root) {
        if (root == null) {
            return;
        }
        solution(root.left);
        if (root.val <= pre) {
            flag = false;
            return;
        }
        pre = root.val;
        solution(root.right);
    }
}
