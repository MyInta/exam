package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/12
 * @describe 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *           0
 *          / \
 *        -3   9
 *        /   /
 *      -10  5
 *
 */
public class I0402sortedArrayToBST {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

//    public TreeNode sortedArrayToBST(int[] nums) {
//        return build(nums, 0, nums.length - 1);
//    }
//    private TreeNode build(int[] nums, int start, int end) {
//        if (end < start) return null;
//        int mid = start + (end - start) / 2;
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = build(nums, start, mid - 1);
//        root.right = build(nums, mid + 1, end);
//        return root;
//    }

    //注意和上面区别在于一个取],一个取),所以mid一个不能包含得mid-1,一个得包含mid 并且前者优先选择中间左边为根，后者靠右
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length);
    }
    private TreeNode build(int[] nums, int start, int end) {
        if (end <= start) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, start, mid);
        root.right = build(nums, mid + 1, end);
        return root;
    }
}
