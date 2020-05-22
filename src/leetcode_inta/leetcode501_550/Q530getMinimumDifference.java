package leetcode_inta.leetcode501_550;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/5/3
 * @describe 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 *
 *
 * 提示：
 *
 *     树中至少有 2 个节点。
 *     本题与 783 相同
 *
 */
public class Q530getMinimumDifference {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //因为是二叉搜索树，所以差值最小肯定是相邻的两个节点，按照中序遍历即可
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        getMin(root);
        return res;
    }
    private int res = Integer.MAX_VALUE;
    private TreeNode pre = null;
    private void getMin(TreeNode root) {
        if (root == null) return;
        getMin(root.left);
        if (pre != null) res = Math.min(res, Math.abs(pre.val - root.val));
        pre = root;
        getMin(root.right);
    }
}
