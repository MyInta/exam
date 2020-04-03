package interview.I1_5;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/4/3
 * @describe 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 *
 */
public class I0406inorderSuccessor {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //看了大神的解答，真实如醍醐灌顶，利用了二叉搜索树的特性
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return root;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        //如果当前结点值大于p，可能是当前p，也可能在其左子树中
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }

    //或者是简单的中序遍历过程，记录下前一个结点
    private TreeNode pre = null;
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return root;
        TreeNode left = inorderSuccessor(root.left, p);
        if (left != null) return left;
        if (pre != null && pre.val == p.val) return root;
        pre = root;
        return inorderSuccessor(root.right, p);
    }
}
