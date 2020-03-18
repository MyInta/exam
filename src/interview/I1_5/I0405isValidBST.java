package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/18
 * @describe 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class I0405isValidBST {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //中序遍历递增
    private long cur = Long.MIN_VALUE;
    private boolean mark = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        dfs(root);
        return mark;
    }
    private void dfs(TreeNode root) {
        if (root.left != null) dfs(root.left);
        if (cur >= root.val) {
            mark = false;
            return;
        }
        cur = root.val;
        if (root.right != null) dfs(root.right);
    }
}
