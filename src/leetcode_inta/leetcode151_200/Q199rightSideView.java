package leetcode_inta.leetcode151_200;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/9
 * @describe 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 */
public class Q199rightSideView {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //层次遍历只保留最右侧解即可
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            //保留当前层所有元素的数量，用于挨个都遍历
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                //一旦到该层的最后位置，就是我们需要的从右往左看的第一个解
                if (i == size - 1) res.add(temp.val);
                //队列先进先出，我们层次是从左到右，所以让左先进
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return res;
    }


    //大佬解答，使用深入遍历
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }
    /**
     *
     * @param res 结果集
     * @param root 遍历到的节点
     * @param dept 层次深度
     */
    private void dfs(List<Integer> res, TreeNode root, int dept) {
        if (root == null) return;
        //一旦深度到了结果集的长度，就赋值，因为我们都是优先右边遍历，不用担心找错值
        if (res.size() == dept) res.add(root.val);
        dfs(res, root.right, dept + 1);
        dfs(res, root.left, dept + 1);
    }
}
