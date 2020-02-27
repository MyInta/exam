package offer.V1_50;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/2/26
 * @describe 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
 * 返回：
 *
 * [3,9,20,15,7]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 1000
 */
public class V32_1levelOrder {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //不就是层次打印。。。一个用层次，一个深入遍历，还可以分为递归和非递归，一共四种表达方式
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                res.add(temp.val);
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //大神直接操作，不需要记录数量,效率极高
    public int[] levelOrder2(TreeNode root) {
        if (root == null) return new int[0];
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        int index = 0;
        while (index < list.size()) {
            TreeNode temp = list.get(index ++);
            if (temp.left != null) list.add(temp.left);
            if (temp.right != null) list.add(temp.right);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            res[i] = list.get(i).val;
        }
        return res;
    }
}
