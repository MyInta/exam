package tencent.leetcode101_150;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/10/20
 * @describe 给定一个二叉树，返回其节点值的锯齿形层次遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 */
public class Q103zigzagLevelOrder {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //bfs
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode cur = root;
        queue.add(cur);
        boolean isreverse = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            //需要记录一层有几个
            int count = queue.size();
            for (int i = 0; i < count; i ++) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (isreverse) {
                    level.add(cur.val);
                } else {
                    level.add(0, cur.val);
                }
            }
            res.add(level);
            //翻转
            isreverse = !isreverse;
        }
        return res;
    }

    //dfs
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        solution(res, root, 0);
        return res;
    }
    private void solution(List<List<Integer>> res, TreeNode root, int dept) {
        if (root == null) return;
        //说明在同一层时
        if (res.size() == dept) {
            res.add(new LinkedList<>());
        }
        //如果是偶数时,正常往队列后加，反之，添加到队头
        if ((dept & 1) == 0) {
            res.get(dept).add(root.val);
        } else {
            res.get(dept).add(0, root.val);
        }
        solution(res, root.left, dept + 1);
        solution(res, root.right, dept + 1);
    }
}
