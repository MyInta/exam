package leetcode_inta.leetcode1301_1350;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/6/6
 * @describe 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 *
 *     该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 *
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
 *
 *
 *
 * 示例：
 *
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：18
 * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
 *
 *
 *
 * 提示：
 *
 *     树中节点的数目在 1 到 10^4 之间。
 *     每个节点的值在 1 到 100 之间。
 */
public class Q1315sumEvenGrandparent {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int sumEvenGrandparent(TreeNode root) {
        int res = 0;
        //那就bfs吧，一层层遍历
        if (root == null) return res;
        if (root.val % 2 == 0) {
            if (root.left != null) {
                if (root.left.left != null) {
                    res += root.left.left.val;
                }
                if (root.left.right != null) {
                    res += root.left.right.val;
                }
            }
            if (root.right != null) {
                if (root.right.left != null) {
                    res += root.right.left.val;
                }
                if (root.right.right != null) {
                    res += root.right.right.val;
                }
            }
        }
        res += sumEvenGrandparent(root.left);
        res += sumEvenGrandparent(root.right);
        return res;
    }


    //另一种比较舒适的表达方式
    private int count;
    public int sumEvenGrandparent2(TreeNode root) {
        count = 0;
        solution(null, null, root);
        return count;
    }
    //分别代表祖父、父类以及当前子类
    private void solution(TreeNode grandpa, TreeNode parent, TreeNode cur) {
        if (grandpa != null && grandpa.val % 2 == 0) count += cur.val;
        if (cur.left != null) solution(parent, cur, cur.left);
        if (cur.right != null) solution(parent, cur, cur.right);
    }
}
