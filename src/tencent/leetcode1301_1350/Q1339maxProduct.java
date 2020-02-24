package tencent.leetcode1301_1350;


import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/22
 * @describe 给你一棵二叉树，它的根为 root 。请你删除 1 条边，使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 *
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 * 示例 1：
 *
 * 输入：root = [1,2,3,4,5,6]
 * 输出：110
 * 解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 * 示例 2：
 * 输入：root = [1,null,2,3,4,null,null,5,6]
 * 输出：90
 * 解释：移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
 * 示例 3：
 *
 * 输入：root = [2,3,9,10,7,8,6,5,4,11,1]
 * 输出：1025
 * 示例 4：
 *
 * 输入：root = [1,1]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
 *
 */
public class Q1339maxProduct {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //思路有，怎么实现需要转换下思想，看了讨论区才明白过来，用集合保存后序遍历下的结点值和
    private List<Integer> counts;
    public int maxProduct(TreeNode root) {
        counts = new ArrayList<>();
        int mol = 1_000_000_007;
        int all = sum(root);
        long res = Long.MIN_VALUE;
        for (int count : counts) {
            //注意这里的陷阱，我们比较的是乘积，最后再考虑取余，如果一开始就比较取余值，逻辑出问题
            long plus = (long)count * (all - count);
            if (plus > res) res = plus;
        }
        return (int)(res % mol);
    }
    private int sum(TreeNode root) {
        if (root == null) return 0;
        //根节点值改为子节点值(递归下去)和本身值和
        int v = root.val + sum(root.left) + sum(root.right);
        //到尽头回退的时候，开始挨个添加到集合中
        counts.add(v);
        return v;
    }
}
