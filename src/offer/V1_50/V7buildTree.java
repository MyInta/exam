package offer.V1_50;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/2/17
 * @describe 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 注意：本题与主站 105 题重复
 */
public class V7buildTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //使用map来保存中序索引信息
    private Map<Integer, Integer> inOrderMap;
    private int[] preorder;
    private int cur = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inOrderMap = new HashMap<>();
        this.preorder = preorder;
        int index = 0;
        for (int i : inorder) {
            inOrderMap.put(i, index ++);
        }
        return build(0, index - 1);
    }
    private TreeNode build(int start, int end) {
        if (start > end) return null;
        int v = preorder[cur ++];
        TreeNode root = new TreeNode(v);
        root.left = build(start, inOrderMap.get(v) - 1);
        root.right = build(inOrderMap.get(v) + 1, end);
        return root;
    }
}
