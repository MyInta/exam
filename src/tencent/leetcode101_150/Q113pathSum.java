package tencent.leetcode101_150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/5
 * @describe 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */
public class Q113pathSum {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        dfs(root, res, sum, new LinkedList<>());
        return res;
    }
//    private void dfs(TreeNode root, List<List<Integer>> res, int sum, List<Integer> list) {
//        //不确定是否存在节点值为负数情况下，不判断
//        //        if (sum < 0) return;
//        list.add(root.val);
//        if (root.val == sum && root.left == null && root.right == null) {
////            list.add(sum);
//            res.add(new LinkedList<>(list));
//            return;
//        }
////        list.add(root.val);
//        if (root.left != null) {
//            dfs(root.left, res, sum - root.val, list);
//            list.remove(list.size() - 1);
//        }
//        if (root.right != null) {
//            dfs(root.right, res, sum - root.val, list);
//            list.remove(list.size() - 1);
//        }
//    }

    //看了大神的思路，简化代码
    private void dfs(TreeNode root, List<List<Integer>> res, int sum, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new LinkedList<>(list));
        }
        dfs(root.left, res, sum - root.val, list);
        dfs(root.right, res, sum - root.val, list);
        list.remove(list.size() - 1);
    }
}
