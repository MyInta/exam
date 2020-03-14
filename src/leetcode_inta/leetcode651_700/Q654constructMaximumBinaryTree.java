package leetcode_inta.leetcode651_700;

/**
 * @author inta
 * @date 2019/12/14
 * @describe 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 * 示例 ：
 *
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 *  
 *
 * 提示：
 *
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 */
public class Q654constructMaximumBinaryTree {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //不要轻易尝试数组拷贝操作，效率实在是低，建议用原数组，但是添加范围限值的方式来“截取”需要段落
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }
    private TreeNode construct(int[] nums, int l, int r) {
        //当越界的时候，直接返回即可
        if (l > r) return null;
        //找到数组中当前范围内最大值的索引
        int index = solution(nums, l, r);
        TreeNode root = new TreeNode(nums[index]);
        root.left = construct(nums, l, index - 1);
        root.right = construct(nums, index + 1, r);
        return root;
    }
    //找到数组最大元素所在索引
    private int solution(int[] nums, int l, int r) {
        int index = l;
        int temp = nums[l];
        for (int i = 1 + l; i <= r; i ++) {
            if (nums[i] > temp) {
                index = i;
                temp = nums[i];
            }
        }
        return index;
    }
}
