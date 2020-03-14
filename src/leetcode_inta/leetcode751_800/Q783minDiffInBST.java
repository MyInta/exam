package leetcode_inta.leetcode751_800;

/**
 * @author inta
 * @date 2020/2/5
 * @describe 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * 注意：
 *
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 *
 */
public class Q783minDiffInBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //根据bst特性，中序遍历数组为升序数组，从中找差值即可
    private long res = Integer.MAX_VALUE;
    private long cur = Integer.MIN_VALUE;
    public int minDiffInBST(TreeNode root) {
        //题意说了二叉树的大小范围在 2 到 100。
        method(root);
        return (int) res;
    }
    private void method(TreeNode root) {
        if (root.left != null) {
            method(root.left);
        }
        res = Math.min(res, root.val - cur);
        cur = root.val;
        if (root.right != null) {
            method(root.right);
        }
    }

    /**
     *     //根据bst特性，中序遍历数组为升序数组，从中找差值即可
     *     private int res = Integer.MAX_VALUE;
     *     private TreeNode cur = null;
     *     public int minDiffInBST(TreeNode root) {
     *         if (root == null) return 0;
     *         method(root);
     *         return res;
     *     }
     *     private void method(TreeNode root) {
     *         if (root.left != null) {
     *             method(root.left);
     *         }
     *         if (cur != null) {
     *             res = Math.min(res, root.val - cur.val);
     *         }
     *         cur = root;
     *         if (root.right != null) {
     *             method(root.right);
     *         }
     *     }
     */
}
