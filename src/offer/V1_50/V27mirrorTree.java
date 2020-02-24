package offer.V1_50;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/22
 * @describe 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *  
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 * 同leetcode226
 */
public class V27mirrorTree {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        //保存下左节点开头
        TreeNode dummy = root.left;
        root.left = mirrorTree(root.right);
        //此时的root.left已经被修改，所以我们得用上前面保存的左节点开头信息
        root.right = mirrorTree(dummy);
        return root;
    }

    //可以使用迭代来做
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                TreeNode temp = q.poll();
                TreeNode l = temp.left;
                temp.left = temp.right;
                temp.right = l;
                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }
        }
        return root;
    }
}
