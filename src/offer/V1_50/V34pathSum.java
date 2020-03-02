package offer.V1_50;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/2
 * @describe 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
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
 *
 * 提示：
 *
 * 节点总数 <= 10000
 *  LC113
 */
public class V34pathSum {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //dfs记录数据
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new LinkedList<>();
        if (root == null) return res;
        dfs(root, sum, 0, new LinkedList<>());
        return res;
    }
    private List<List<Integer>> res;
    private void dfs(TreeNode root, int sum, int count, LinkedList<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        count += root.val;
        if (root.left == null && root.right == null) {
            if (sum == count) {
                res.add(new LinkedList<>(list));
            }
            //下面两行可省
            list.removeLast();
            return;
        }
        dfs(root.left, sum, count, list);
        dfs(root.right, sum, count, list);
        list.removeLast();
    }
}
