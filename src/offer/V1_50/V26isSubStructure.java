package offer.V1_50;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/2/25
 * @describe 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 */
public class V26isSubStructure {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
//    public boolean isSubStructure(TreeNode A, TreeNode B) {
//        //bfs A，找B匹配情况
//        if (B == null || A == null) return false;
//        int start = B.val;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(A);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i ++) {
//                TreeNode temp = queue.poll();
//                if (temp.val == start && isMatch(temp, B)) return true;
//                if (temp.left != null) queue.add(temp.left);
//                if (temp.right != null) queue.add(temp.right);
//            }
//        }
//        return false;
//    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        return isMatch(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    //判断两树是否匹配
    private boolean isMatch(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;
        return isMatch(A.left, B.left) && isMatch(A.right, B.right);
    }
}
