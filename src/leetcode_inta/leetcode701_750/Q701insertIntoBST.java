package leetcode_inta.leetcode701_750;

/**
 * @author inta
 * @date 2020/2/12
 * @describe 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
 * 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 *
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 *
 * 例如, 
 *
 * 给定二叉搜索树:
 *
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * 和 插入的值: 5
 * 你可以返回这个二叉搜索树:
 *
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 * 或者这个树也是有效的:
 *
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 */
public class Q701insertIntoBST {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode newNode = new TreeNode(val);
        while (cur != null) {
            if (cur.val > val) {
                if (cur.left == null) {
                    cur.left = newNode;
                    return root;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = newNode;
                    return root;
                }
                cur = cur.right;
            }
        }
        return newNode;
    }
}
