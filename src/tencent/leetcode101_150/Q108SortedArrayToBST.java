package tencent.leetcode101_150;

/**
 * @author inta
 * @date 2019/7/17
 * @describe 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class Q108SortedArrayToBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    int[] temp;

    public TreeNode search(int left,int right){
       if(left>right){
           return null;
       }
       int mid = left+((right-left)>>1);
       TreeNode t = new TreeNode(temp[mid]);
       t.left = search(left,mid-1);
       t.right = search(mid+1,right);
       return t;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        temp = nums;
        return search(0,nums.length-1);
    }

}
