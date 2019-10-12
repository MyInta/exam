package tencent.leetcode501_550;

/**
 * @author inta
 * @date 2019/10/12
 * @describe 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */
public class Q543diameterOfBinaryTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    private int res;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res;
    }
    private int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    //使用传值来进行记录与递归
    public int diameterOfBinaryTree2(TreeNode root) {
        int[] maxDepth = new int[1];
        return depth(root, maxDepth);
    }
    private int depth(TreeNode root, int[] maxDepth) {
        if (root == null) {
            maxDepth[0] = 0;
            return 0;
        }
        int leftMax = depth(root.left, maxDepth);
        int left = maxDepth[0];
        int rightMax = depth(root.right, maxDepth);
        int right = maxDepth[0];
        maxDepth[0] = Math.max(left, right) + 1;
        return Math.max(Math.max(left + right, leftMax), rightMax);
    }
}
