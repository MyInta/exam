package tencent.leetcode101_150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/23
 * @describe 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 */
public class Q129sumNumbers {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = val;
        }
    }
    //本能dfs
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        List<List<Integer>> lists = new ArrayList<>();
        dfs(lists, new ArrayList<>(), root);
        //此时已经获得所有list集合
        int res = 0;
        for (List<Integer> l : lists) {
            //累加每个list内元素组成的数
            int num = 0;
            for (int x : l) {
                num = num * 10 + x;
            }
            res += num;
        }
        return res;
    }
    private void dfs(List<List<Integer>> lists, List<Integer> list, TreeNode root) {
        list.add(root.val);
        //遇到叶子节点就添加并返回
        if (root.left == null && root.right == null) {
            lists.add(new ArrayList<>(list));
            return;
        }
        if (root.left != null) {
            dfs(lists, list, root.left);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            dfs(lists, list, root.right);
            list.remove(list.size() - 1);
        }
    }

    //大神的递归操作
    public int sumNumbers2(TreeNode root) {
        solution(root, 0);
        return addRes;
    }
    private int addRes = 0;
    private void solution(TreeNode root, int cur) {
        if (root == null) return;
        int sum = cur * 10 + root.val;
        if (root.left == null && root.right == null) {
            addRes += sum;
        }
        if (root.left != null) {
            solution(root.left, sum);
        }
        if (root.right != null) {
            solution(root.right, sum);
        }
    }

    //大神的非递归，栈操作
    public int sumNumbers3(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Stack<TreeNode> tn_stack = new Stack<>();
        Stack<Integer> num_stack = new Stack<>();
        tn_stack.add(root);
        num_stack.add(0);
        while (!tn_stack.isEmpty()) {
            TreeNode temp = tn_stack.pop();
            int cur = num_stack.pop() * 10 + temp.val;
            if (temp.left == null && temp.right == null) {
                res += cur;
            }
            if (temp.left != null) {
                tn_stack.add(temp.left);
                num_stack.add(cur);
            }
            if (temp.right != null) {
                tn_stack.add(temp.right);
                num_stack.add(cur);
            }
        }
        return res;
    }

    //还有大神提出的分治思想 和递归一种性质
    public int sumNumbers4(TreeNode root) {
        if (root == null) return 0;
        return divide(root, 0);
    }
    private int divide(TreeNode root, int sum) {
        sum = sum * 10 + root.val;
        //如果已经是叶子节点的时候添加并返回值
        if (root.left == null && root.right == null) {
            return sum;
        }
        int ans = 0;
        if (root.left != null) ans += divide(root.left, sum);
        if (root.right != null) ans += divide(root.right, sum);
        return ans;
    }
}
