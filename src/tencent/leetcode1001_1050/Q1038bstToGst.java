package tencent.leetcode1001_1050;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/9
 * @describe 给出二叉搜索树的根节点，该二叉树的节点值各不相同，
 * 修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键小于节点键的节点。
 * 节点的右子树仅包含键大于节点键的节点。
 * 左右子树也必须是二叉搜索树。
 *  
 *
 * 示例：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *  
 *
 * 提示：
 *
 * 树中的节点数介于 1 和 100 之间。
 * 每个节点的值介于 0 和 100 之间。
 * 给定的树为二叉搜索树。
 *  
 */
public class Q1038bstToGst {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //这个需要看图，才能清晰理解题意，是中序遍历的变形，更改结点值即可,一般我喜欢用迭代，步骤更清晰些
    public TreeNode bstToGst2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int temp = 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            //修改val值，并保存累加值
            cur.val = cur.val + temp;
            temp = cur.val;
            //找左节点
            cur = cur.left;
        }
        return root;
    }

    //用递归解,更易于理解
    private int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return root;
        if (root.right != null) bstToGst(root.right);
        //如果右节点到叶子节点，操作一波值
        root.val = root.val + sum;
        sum = root.val;
        if (root.left != null) bstToGst(root.left);
        return root;
    }
}
