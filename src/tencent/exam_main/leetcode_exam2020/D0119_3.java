package tencent.exam_main.leetcode_exam2020;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/19
 * @describe
 */
public class D0119_3 {
      private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    private boolean changed = true;
    private TreeNode dumpy = new TreeNode(0);
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        changed = false;
        dumpy.left = root;
        if (root == null) return root;
        if (root.left == null && root.right == null && root.val == target) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.left != null && temp.left.left == null && temp.left.right == null && temp.left.val == target) {
                temp.left = null;
                changed = true;
            }
            if (temp.right != null && temp.right.left == null && temp.right.right == null && temp.right.val == target) {
                temp.right = null;
                changed = true;
            }
            if (temp.right != null) {
                stack.add(temp.right);
            }
            if (temp.left != null) {
                stack.add(temp.left);
            }
        }
        if (changed) {
            dumpy.left = removeLeafNodes(root, target);
        }
        return dumpy.left;
    }

}
