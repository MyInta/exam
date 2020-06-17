package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2020/6/17
 * @describe 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 *
 * 输出:
 *
 * 2
 *
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 *
 * 输出:
 *
 * 2
 *
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class Q687longestUnivaluePath {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //路径在左边，在右边，包括中间
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return distance;
    }
    private int distance = 0;
    private int dfs(TreeNode node) {
        if (node== null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        left = node.left != null && node.left.val == node.val ? left + 1 : 0;
        right = node.right != null && node.right.val == node.val? right + 1 : 0;
        //结果是子解中的最大值
        distance = Math.max(distance, left + right);
        //返回路径中最长距离
        return Math.max(left, right);
    }
}
