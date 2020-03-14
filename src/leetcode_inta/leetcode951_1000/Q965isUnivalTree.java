package leetcode_inta.leetcode951_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/1/3
 * @describe 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * 示例 1：
 *
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 *
 * 输入：[2,2,2,5,2]
 * 输出：false
 * 提示：
 *
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 *
 */
public class Q965isUnivalTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = val;
        }
    }

    public boolean isUnivalTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return false;
        int match = root.val;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                if (temp.val != match) return false;
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return true;
    }
}
