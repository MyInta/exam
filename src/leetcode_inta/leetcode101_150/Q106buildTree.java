package leetcode_inta.leetcode101_150;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/11/3
 * @describe 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Q106buildTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        return createTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    private TreeNode createTree(int[] inorder, int[] postorder, int i_l, int i_r, int p_l, int p_r) {
        if (i_l > i_r || p_l > p_r) return null;
        int index = i_l;
        while (inorder[index] != postorder[p_r]) {
            index ++;
        }
        TreeNode root = new TreeNode(postorder[p_r]);
        //p_l + index - i_l - 1 可理解为 p_l基础上，往右数 （i_l非root点的移动的个数（有时候本身为root，所以是0 - 1个））
        root.left = createTree(inorder, postorder, i_l, index - 1, p_l, p_l + index - i_l - 1);
        //同上，理解index - i_l为截取的数量即可
        root.right = createTree(inorder, postorder, index + 1, i_r, p_l + index - i_l, p_r - 1);
        return root;
    }

    //优化：使用hashmap保存中序的所有元素和对应索引，然后只需要考察后序中的元素即可
    private int[] postorder;
    private Map<Integer, Integer> hashMap = new HashMap<>();
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i ++) {
            hashMap.put(inorder[i], i);
        }
        return solution(0, inorder.length - 1, 0 , postorder.length - 1);
    }
    private TreeNode solution(int i_l, int i_r, int p_l, int p_r) {
        if (i_l > i_r || p_l > p_r) return null;
        int root_value = postorder[p_r];
        int index = hashMap.get(root_value);
        TreeNode root = new TreeNode(root_value);
        //各有一个位置不再取，inorder不取index,post不取末端，但两者截取的长度要一致
        root.left = solution(i_l, index - 1, p_l, p_l + index - i_l - 1);
        root.right = solution(index + 1, i_r, p_l + index - i_l, p_r - 1);
        return root;
    }

}
