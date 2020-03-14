package leetcode_inta.leetcode501_550;

/**
 * @author inta
 * @date 2019/10/9
 * @describe 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 */
public class Q538convertBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        TreeNode cur = new TreeNode(root.val);
        if (root.right != null) {
            cur.right = convertBST(root.right);
        }
        cur.val += sum;
        sum = cur.val;
        if (root.left != null) {
            cur.left = convertBST(root.left);
        }
        return cur;
    }

    //简洁版本
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        root.val += sum;
        sum = root.val;
        convertBST(root.left);
        return root;
    }
}
