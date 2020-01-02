package tencent.leetcode501_550;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/31
 * @describe 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 *
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *  
 *
 * 示例 2:
 *
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 *  
 *
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 */
public class Q513findBottomLeftValue {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //层次遍历找最左
    public int findBottomLeftValue(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        //题意说她不为null
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode tn = queue.poll();
                if (i == 0) res = tn.val;
                if (tn.left != null) {
                    queue.add(tn.left);
                }
                if (tn.right != null) {
                    queue.add(tn.right);
                }
            }
        }
        return res;
    }

    //递归
    public int findBottomLeftValue2(TreeNode root) {
        findLeft(root, 0);
        return res;
    }
    private int res = -1, max_dept = -1;
    private void findLeft(TreeNode root, int dept) {
        if (root == null) return;
        findLeft(root.left, dept + 1);
        if (dept > max_dept) {
            max_dept = dept;
            res = root.val;
        }
        findLeft(root.right, dept + 1);
    }

    //大佬的解题方式
    public int findBottomLeftValue3(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        TreeNode cur = root;
        //核心在于顺序是从右往左一层一层加到list中，最终剩余的是list的最底层靠左元素
        for (int i = 0; i < list.size(); i ++) {
            cur = list.get(i);
            if (cur.right != null) {
                list.add(cur.right);
            }
            if (cur.left != null) {
                list.add(cur.left);
            }
        }
        return cur.val;
    }

}
