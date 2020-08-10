package leetcode_inta.exam_main.leetcode_exam2020.D199;


/**
 * @author inta
 * @date 2020/7/26
 * @describe
 */
public class Q3 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int countPairs(TreeNode root, int distance) {
        if (distance < 2 || root == null) return 0;
        int sum = countPairs(root.left, distance) + countPairs(root.right, distance);
        for (int i = 0; i < distance - 1; i++) {
            for (int j = 0; j <= distance - i - 2; j++) {
                sum += dfs(root.left, i) * dfs(root.right, j);
            }
        }
        return sum;
    }
    private int dfs(TreeNode root, int dis) {
        if (root == null || dis < 0) return 0;
        if (root.left == null && root.right == null && dis == 0) return 1;
        return dfs(root.left, dis - 1) + dfs(root.right, dis - 1);
    }

}
