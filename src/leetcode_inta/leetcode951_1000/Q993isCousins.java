package leetcode_inta.leetcode951_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/3/31
 * @describe 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 *
 * 示例 3：
 *
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *
 *
 *
 * 提示：
 *
 *     二叉树的节点数介于 2 到 100 之间。
 *     每个节点的值都是唯一的、范围为 1 到 100 的整数。
 *
 */
public class Q993isCousins {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //层次遍历，一层层找有没有x 和 y
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null && temp.right != null) {
                    if (temp.left.val == x && temp.right.val == y ||
                            temp.left.val == y && temp.right.val == x) return false;
                }
                if (temp.val == x || temp.val == y) {
                    if (!flag) {
                        flag = true;
                    } else {
                        return true;
                    }
                }
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            if (flag) return false;
        }
        return false;
    }
}
