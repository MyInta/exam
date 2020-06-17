package leetcode_inta.leetcode1001_1050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/6/15
 * @describe 给出一棵二叉树，其上每个结点的值都是 0 或 1 。
 * 每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 以 10^9 + 7 为模，返回这些数字之和。
 *
 *
 *
 * 示例：
 *
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 *
 *
 * 提示：
 *
 *     树中的结点数介于 1 和 1000 之间。
 *     node.val 为 0 或 1 。
 *
 */
public class Q1022sumRootToLeaf {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //dfs
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        strs = new ArrayList<>();
        build(root, new StringBuilder());
        int res = 0;
        for (String str : strs) {
            res += Integer.valueOf(str, 2);
            res %= 1_000_000_007;
        }
        return res;
    }
    private List<String> strs;
    private void build(TreeNode root, StringBuilder sb) {
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            strs.add(sb.toString());
            if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
            return;
        }
        if (root.left != null) build(root.left, sb);
        if (root.right != null) build(root.right, sb);
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
    }

    //看了评论区，智商被碾压
    public int sumRootToLeaf2(TreeNode root) {
        if (root == null) return 0;
        int value = root.val;
        //如果到尽头了,就返回值
        if (root.left == null && root.right == null) return value;
        if (root.left != null) root.left.val += (value << 1) % 1_000_000_007;
        if (root.right != null) root.right.val += (value << 1) % 1_000_000_007;
        //目前修改掉了第一层的数值，接下来一层层修改数值，因为需要返回的是底层所有数累和，也就是当前层下一层值累和
        return sumRootToLeaf(root.left) + sumRootToLeaf(root.right);
    }
}
