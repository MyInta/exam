package offer.V51_100;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/24
 * @describe 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class V54kthLargest {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //反转中序遍历,就是由大到小了
    public int kthLargest(TreeNode root, int k) {
        if (root == null) return -1;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            if (k == 1) return cur.val;
            k --;
            cur = cur.left;
        }
        return -1;
    }

    //用递归做做
    public int kthLargest2(TreeNode root, int k) {
        if (root == null) return -1;
        this.count = 0;
        this.k = k;
        this.res = -1;
        recursion(root);
        return res;
    }
    private int count;
    private int k;
    private int res;
    private void recursion(TreeNode root) {
        if (root == null) return;
        recursion(root.right);
        if (++ count == k) {
            res = root.val;
            return;
        }
        recursion(root.left);
    }
}
