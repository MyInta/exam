package tencent.leetcode201_300;

/**
 * @author inta
 * @date 2019/7/17
 * @describe 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 *
 */
public class Q230kthSmallest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int cur;
    int res = -1;
    public void search(TreeNode t){
        if(t==null){
            return;
        }
        search(t.left);
        cur--;
        if(cur==0){
            res = t.val;
        }
        search(t.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        cur = k;
        search(root);
        return res;
    }
}
