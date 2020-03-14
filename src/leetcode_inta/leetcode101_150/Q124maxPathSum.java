package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/9/25
 * @describe 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 *
 */
public class Q124maxPathSum {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        solution(root);
        return maxSum;
    }
    //根源为root root.left 和root.right maxSum记录三点组合的最大值
    private int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //可以理解为记录1：左点的值
        int left = solution(root.left);
        //记录2：右点的值
        int right = solution(root.right);
        //记录3：一点加root的值
        int oneSize = root.val + Math.max(0, Math.max(left, right));
        //记录4：两点加root的值
        int allSize = root.val + Math.max(0, left) + Math.max(0, right);
        //其中记录1和2都是记录一点的，为一个类型，maxSum记录一点、一点一root、两点一root的最大值情况
        maxSum = Math.max(maxSum, Math.max(oneSize, allSize));
        //返回的时候是考虑上层建筑，将一点一root理解为新的一点，与新父节点和一个子节点构成三点组合
        // 从最优解角度思考肯定是选择一点一root最大的那个即oneSize
        return oneSize;
    }
}
