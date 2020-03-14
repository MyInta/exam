package leetcode_inta.leetcode501_550;

import java.util.*;

/**
 * @author inta
 * @date 2020/1/25
 * @describe 您需要在二叉树的每一行中找到最大的值。
 *
 * 示例：
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 输出: [1, 3, 9]
 *
 */
public class Q515largestValues {
    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    //层次遍历，找最大？
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size;i ++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                max = Math.max(max, temp.val);
            }
            res.add(max);
        }
        return res;
    }

    //递归写法
    private List<Integer> res = new ArrayList<>();
    public List<Integer> largestValues2(TreeNode root) {
        if (root == null) return res;
        findMax(root, 0);
        return res;
    }
    private void findMax(TreeNode root, int dept) {
        //如果深度到了下一层，那么就扩充集合尺寸
        if (dept == res.size()) {
            res.add(dept, root.val);
        }
        //如果没有到下一层，就是在原层基础上，更迭最大值
        res.set(dept, Math.max(res.get(dept), root.val));
        //并继续遍历子节点
        if (root.left != null) {
            findMax(root.left, dept + 1);
        }
        if (root.right != null) {
            findMax(root.right, dept + 1);
        }
    }
}
