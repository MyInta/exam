package tencent.leetcode101_150;

/**
 * @author inta
 * @date 2019/8/9
 * @describe 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class Q110isBalanced {

    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }

    public boolean res= true;
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        getHeight(root,1);
        return res;
    }

    private int getHeight(TreeNode tn,int level){
        if(tn == null){
            return level;
        }
        int lH = getHeight(tn.left,level+1);
        if(!res){
            return level;
        }
        int rH = getHeight(tn.right,level+1);
        if(!res){
            return level;
        }
        if(Math.abs(lH-rH)>1){
            res = false;
        }
        return lH>rH?lH:rH;
    }
}
