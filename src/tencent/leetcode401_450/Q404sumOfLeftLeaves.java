package tencent.leetcode401_450;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/16
 * @describe 计算给定二叉树的所有左叶子之和。
 *
 * 示例：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q404sumOfLeftLeaves {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> left_stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.right != null) {
                stack.add(temp.right);
            }
            if (temp.left != null) {
                stack.add(temp.left);
                left_stack.add(temp.left);
            }
        }
        int res = 0;
        //遍历所有节点，来查询出所有可能的左节点
        for (TreeNode t : left_stack) {
            if (t.left == null && t.right == null) {
                res += t.val;
            }
        }
        return res;
    }

    //看了大神的解答之后的递归思路
    private int res = 0;
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return res;
        getSum(root);
        return res;
    }
    //递归
    private void getSum(TreeNode root) {
        if (root.left != null) {
            //在左节点不为空的前提下，我们进行判断，看是否是属于叶子
            if (root.left.left == null && root.left.right == null) {
                res += root.left.val;
            } else {
                getSum(root.left);
            }
        }
        if (root.right != null) {
            getSum(root.right);
        }
    }

    //大神的简介操作，看得我鸡皮疙瘩出来了
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) return 0;
        //然后保留三个参数，left，right，cur分别保存左子树得左叶子之和，右子树得左叶子之和以及当前下的左节点为左叶子时候的值
        int left = 0, right = 0, cur = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            cur = root.left.val;
        }
        left = sumOfLeftLeaves3(root.left);
        right = sumOfLeftLeaves3(root.right);
        return cur + left + right;
    }

}
