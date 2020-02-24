package offer.V1_50;

import java.util.*;

/**
 * @author inta
 * @date 2020/2/24
 * @describe 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 *  
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 */
public class V32levelOrder {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //不就是bfs?
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<Integer> list;
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            list = new LinkedList<>();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                if (reverse) {
                    list.add(temp.val);
                } else {
                    list.addFirst(temp.val);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
            }
            //下一次就要反转了
            reverse = !reverse;
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    //还可以用dfs做,深度遍历,考虑在那一层即可,参考LC103

}
