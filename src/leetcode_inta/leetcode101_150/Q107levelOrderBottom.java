package leetcode_inta.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/11/28
 * @describe 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 */
public class Q107levelOrderBottom {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    private class Pair{
        TreeNode pair;
        int dept;
        Pair(TreeNode pair,int dept) {
            this.pair = pair;
            this.dept = dept;
        }
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Pair> queue = new LinkedList<>();
        Stack<List<Integer>> list_stack = new Stack<>();
        int dept = 0;
        List<Integer> list = new ArrayList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode temp = pair.pair;
            int p_dept = pair.dept;
            if (p_dept == dept) {
                list.add(temp.val);
            } else {
                list_stack.add(list);
                dept ++;
                list = new ArrayList<>();
                list.add(temp.val);
            }
            if (temp.left != null) {
                queue.add(new Pair(temp.left, p_dept + 1));
            }
            if (temp.right != null) {
                queue.add(new Pair(temp.right, p_dept + 1));
            }
        }
        res.add(list);
        while (!list_stack.isEmpty()) {
            res.add(list_stack.pop());
        }
        return res;
    }

    //优化后的非递归解法，直接依据队列长度进行判断
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = q.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

    //使用递归算法
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        bfs(root, 0, res);
        Collections.reverse(res);
        return res;
    }
    private void bfs(TreeNode root, int dept, List<List<Integer>> res) {
        if (root == null) return;
        if (dept == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(dept).add(root.val);
        if (root.left != null) {
            bfs(root.left, dept + 1, res);
        }
        if (root.right != null) {
            bfs(root.right, dept + 1, res);
        }
    }
}
