package tencent.leetcode101_150;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/9/23
 * @describe 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class Q114flatten {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //递归方式，变形后的后序遍历（r,l,root）得到 6 5 4 3 2 1同时挨个连接,可以防止正常先序下的子树丢失问题
    private TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    private class Q114flatten2{
        //将右子树插入到左子树最右结点，再移动左子树顶端位置到右边，以此得到
        public void flatten(TreeNode root) {
            while (root != null) {
                if (root.left == null) {
                    root = root.right;
                    continue;//或者将下面语句都用else括起来
                }
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
        }

        //使用先序遍历，需要提前存储子结点，防止丢失
        public void flatten2(TreeNode root) {
            if (root == null) {
                return;
            }
            Stack<TreeNode> s = new Stack<>();
            s.push(root);
            TreeNode pre = null;
            while (!s.isEmpty()) {
                TreeNode temp = s.pop();
                if (pre != null) {
                    pre.right = temp;
                    pre.left = null;
                }
                if (temp.right != null) {
                    s.push(temp.right);
                }
                if (temp.left != null) {
                    s.push(temp.left);
                }
                pre = temp;
            }
        }
    }

}
