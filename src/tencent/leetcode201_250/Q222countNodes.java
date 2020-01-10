package tencent.leetcode201_250;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/9
 * @describe 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
 * 其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 */
public class Q222countNodes {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //如果只是统计结点数，各种遍历都可以啊，bfs，dfs,层次迭代遍历耗时多一些，8ms
    public int countNodes(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = stack.pop();
                res ++;
                if (temp.right != null) stack.add(temp.right);
                if (temp.left != null) stack.add(temp.left);
            }
        }
        return res;
    }
    //同上bfs，使用递归，采用的是前序，也可以更改为后序，更换前后次序而已，不做重复，递归0ms
    private int sum_recursion = 0;
    public int countNodes2(TreeNode root) {
        if (root == null) return sum_recursion;
        sum_recursion ++;
        if (root.left != null) {
            countNodes2(root.left);
        }
        if (root.right != null) {
            countNodes2(root.right);
        }
        return sum_recursion;
    }

    //还有dfs，可以选中序，倒置中序,这里以中序为代表 dfs迭代10ms
    public int countNodes3(TreeNode root) {
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res ++;
            cur = cur.right;
        }
        return res;
    }

    //dfs的递归    0ms
    private int dfs_sum = 0;
    public int countNodes4(TreeNode root) {
        if (root == null) return dfs_sum;
        if (root.left != null) countNodes4(root.left);
        dfs_sum ++;
        if (root.right != null) countNodes4(root.right);
        return dfs_sum;
    }
}
