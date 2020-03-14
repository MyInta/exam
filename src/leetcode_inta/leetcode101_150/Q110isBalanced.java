package leetcode_inta.leetcode101_150;

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

    //20200229 上面不太容易理解，改成这样好理解
    public boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(getDepth(root.left) - getDepth(root.right)) < 2) {
            //在符合提议内，考虑各自的子树深度差
            return isBalanced(root.left) && isBalanced(root.right);
        }
        //否则，两子树深度差超过1，不符合题意，为false
        return false;
    }
    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        //返回树内最大深度
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
