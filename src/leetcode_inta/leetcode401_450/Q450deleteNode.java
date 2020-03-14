package leetcode_inta.leetcode401_450;

/**
 * @author inta
 * @date 2020/1/4
 * @describe 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 */
public class Q450deleteNode {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //看大神搬运的算法讲解中关于使用后驱结点代替被删结点的方式successor,回头自己实现下若用前驱结点predecessor替代
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                //如果要删去root，就把root右子树最左结点为新的root
                TreeNode dummy = min(root.right);
                //左右结点连接顺序不能调换，会出错
                dummy.right = deleteMin(root.right);
                dummy.left = root.left;
                return dummy;
            }
        }
    }
    //找到子节点最小的
    private TreeNode min(TreeNode root) {
        if (root.left == null) return root;
        return min(root.left);
    }
    //返回删除了最小左节点的链表
    private TreeNode deleteMin(TreeNode root) {
        if (root.left == null) return root.right;
        //如果没找到，一直找，找到最左结点为止，将其去除，增加左节点为右节点
        root.left =  deleteMin(root.left);
        return root;
    }


    //使用前驱结点predecessor代替被删结点
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return root;
        //当root值更大，说明root是被删结点的父节点且在右边
        if (root.val > key) {
            root.left = deleteNode2(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode2(root.right, key);
            return root;
        }
//        assert root.val == key;
        //在确认找到需要删除结点情况下，判断其左右结点有无空，有直接返回另一支线即可
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
//        assert root.left != null && root.right != null;
        //在发现被删结点左右两子节点都不为空的情况下，这次我们选用前驱结点，即左子树的最右结点为顶替方
        TreeNode predecessor = findMax(root.left);
        //拷贝predecessor值作为新的父节点
        TreeNode predecessorCopy = new TreeNode(predecessor.val);
        //其左节点为删除掉最右结点的左节点链
        predecessorCopy.left = deleteMax(root.left);
        //其右节点就是原先的右节点
        predecessorCopy.right = root.right;
        //并且把原先的结点左右两方向都作null，即删除操作
        root.left = null;
        root.right = null;
        //返回新建的结点
        return predecessorCopy;
    }
    //找寻该树最右结点
    private TreeNode findMax(TreeNode root) {
        if (root.right != null) return findMax(root.right);
        return root;
    }
    //找寻到该树最右结点，将父节点把右节点更换为左节点
    private TreeNode deleteMax(TreeNode root) {
        //找到最右结点，直接返回其左节点，就相当于此时的母结点不可用，我们删除了该结点了，用其左节点开头
        if (root.right == null) return root.left;
        //若不为null，就一直连接右子节点方向找最右结点，最终记得我们返回的是母结点root
        root.right = deleteMax(root.right);
        return root;
    }

}
