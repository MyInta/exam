package leetcode_inta.leetcode51_100;

import java.util.*;

/**
 * @author inta
 * @date 2020/1/3
 * @describe 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class Q99recoverTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    //用最蠢的方法先实现下,中序遍历找逆差：前一节点和后一结点的关系，逆序情况下的前一结点和找到第一个逆序之后，逆差的后一结点
    //即找出逆差大的值和逆差小的值
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        //当前结点
        TreeNode cur = root;
        //设一个当前结点后一个结点情况
        TreeNode cur_pre = new TreeNode(Integer.MIN_VALUE);
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            //一直深入添加存在的左节点
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            //遇到最后一个子左节点为空情况下，取出该父节点
            cur = stack.pop();
            //当前一个结点值大于后一个结点值，即出现逆差时候
            if (first == null && cur_pre.val > cur.val) {
                //当第一个逆差位置都还没找到的时候，我们先把逆序的两个位置保存
                first = cur_pre;
                second = cur;
            } else if (cur_pre.val > cur.val) {
                //说明已经找到了一个逆差，而又出现逆差，说明原链表交换的位置不相连，更新后一个逆差值
                second = cur;
                break;
            }
            //更新前一结点为中序遍历的前一个位置
            cur_pre = cur;
            //深入后一结点
            cur = cur.right;
        }
        //最后，因为我们已经遍历中序所有元素，并找到了逆差的两个元素一大一小，直接交换
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    //使用递归解法
    private TreeNode firstNode;
    private TreeNode secondNode;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree2(TreeNode root) {
        recruit(root);
        //由上面递归找到了逆差的两个结点，交换
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }
    private void recruit(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            recruit(root.left);
        }
        if (firstNode == null && pre.val > root.val) {
            firstNode = pre;
            secondNode = root;
        }
        if (firstNode != null && pre.val > root.val) {
            secondNode = root;
        }
        //更新结点
        pre = root;
        if (root.right != null) {
            recruit(root.right);
        }
    }

}
