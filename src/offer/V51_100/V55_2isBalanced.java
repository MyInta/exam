package offer.V51_100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/2/29
 * @describe 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 限制：
 *
 * 1 <= 树的结点个数 <= 10000
 *  LC110
 */
public class V55_2isBalanced {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private boolean valid;
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        valid = true;
        getH(root, 1);
        return valid;
    }
    private int getH(TreeNode root, int level) {
        if (root == null) return level;
        int lH = getH(root.left, level + 1);
        int rH = getH(root.right, level + 1);
        if (Math.abs(lH - rH) > 1) valid = false;
        return Math.max(lH, rH);
    }

}
