package tencent.leetcode651_700;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/11
 * @describe 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 * 示例 2:
 *
 * 输入:
 *     2
 *    / \
 *   2   2
 *
 * 输出: -1
 * 说明: 最小的值是 2, 但是不存在第二小的值。
 *
 */
public class Q671findSecondMinimumValue {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //大神给了思路，求左右子树最小值，比较根值，都大于根值，取较小的，否则，取较大的
    public int findSecondMinimumValue(TreeNode root) {
        return searchMin(root, root.val);
    }
    private int searchMin(TreeNode root, int val) {
        if (root == null) return -1;
        if (root.val > val) return root.val;
        int left = searchMin(root.left, root.val);
        int right = searchMin(root.right, root.val);
        if (left > val && right > val) {
            return Math.min(left, right);
        } else {
            //至少有一方小于等于父结点值,直接返回两者中的最大值即可,若没有大于父节点的，都会成为-1
            return Math.max(left, right);
        }
    }

    //标记的方式，来判断有无改变
    private int first = Integer.MAX_VALUE, second = first;
    private boolean changed = false;
    public int findSecondMinimumValue2(TreeNode root) {
        if (root == null) return -1;
        if (root.val < first) {
            //如果发现比最小的小，就替换掉
            second = first;
            first = root.val;
        } else if (root.val > first && root.val <= second) {
            //当它夹在第一第二之间，它就是第二,注意等于，是担心结点存在Integer的MAX_VALUE()值
            second = root.val;
            //标记下，的确，第二名改变过了，它现在可以独当一面咯
            changed = true;
        }
        //继续找下一个
        findSecondMinimumValue2(root.left);
        findSecondMinimumValue2(root.right);
        //问问，小伙砸，你有没有做过一些让自己改变的事呀，没有，那你走吧
        return changed ? second : -1;
    }
}
