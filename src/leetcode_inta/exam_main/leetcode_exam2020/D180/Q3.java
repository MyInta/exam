package leetcode_inta.exam_main.leetcode_exam2020.D180;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/15
 * @describe
 */
public class Q3 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //中序遍历生成递增数组，然后切割获得平衡二叉树
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        list = new ArrayList<>();
        buildList(root);
        return buildTree(list, 0, list.size() - 1);
    }
    private List<TreeNode> list;
    private void buildList(TreeNode root) {
        if (root.left != null) buildList(root.left);
        list.add(root);
        if (root.right != null) buildList(root.right);
    }
    private TreeNode buildTree(List<TreeNode> list, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = list.get(mid);
        root.left = buildTree(list, left, mid - 1);
        root.right = buildTree(list, mid + 1, right);
        return root;
    }
}
