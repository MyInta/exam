package leetcode_inta.leetcode901_950;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/11/2
 * @describe 给定二叉搜索树的根结点 root，中序遍历后返回 L 和 R（含）之间的所有结点的值的和。
 * 二叉搜索树保证具有唯一的值。
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 输出：32
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 输出：23
 * 提示：
 * 树中的结点数量最多为 10000 个。
 * 最终的答案保证小于 2^31。
 */
public class Q938rangeSumBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 使用的递归
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }
    }
    // 使用非递归，迭代
    public int rangeSumBST2(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                if (cur.val <= R && cur.val >= L) {
                    ans += cur.val;
                }
                if (cur.val > L) {
                    stack.add(cur.left);
                }
                if (cur.val < R) {
                    stack.add(cur.right);
                }
            }
        }
        return ans;
    }

    // 简单思路 中序遍历累加值
    private int sum = 0;
    private int low;
    private int high;
    public int rangeSumBST3(TreeNode root, int low, int high) {
        this.low = low;
        this.high = high;
        inOrder(root);
        return sum;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        if (root.val >=  low && root.val <= high) {
            sum += root.val;
        }
        if (root.right != null) {
            inOrder(root.right);
        }
    }
}
