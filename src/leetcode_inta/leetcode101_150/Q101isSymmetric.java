package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/8/14
 * @describe 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class Q101isSymmetric {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return checkChild(root.left,root.right);
    }
    public boolean checkChild(TreeNode child1,TreeNode child2){
        if(child1==null&&child2==null){
            return true;
        }
        if(child1!=null&&child2!=null&&child1.val == child2.val){
            return checkChild(child1.left,child2.right)&&checkChild(child1.right,child2.left);
        }
        return false;
    }
}
