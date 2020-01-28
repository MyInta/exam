package tencent.leetcode1301_1350;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/1/27
 * @describe 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 *
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *  
 *
 * 提示：
 *
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 *
 */
public class Q1302deepestLeavesSum {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //。。。。。。。。bfs就可以了,先用迭代,我的方法想想能解出，但冗余计算太多，需要优化
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = 0;
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                res += temp.val;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return res;
    }

    //dfs解法，大神使用了一个全局变量来记录最深和总和
    private int MAX_DEP = 0;
    private int res = 0;
    public int deepestLeavesSum2(TreeNode root) {
        if (root == null) return res;
        dfs(root, 0);
        return res;
    }
    private void dfs(TreeNode root, int depth) {
        //如果在最大深度维度上，全局量计合和累加
        if (depth == MAX_DEP) {
            res += root.val;
        }
        //若出现新深度，就重置最深记录，并且初始化计合为该元素值
        if (depth > MAX_DEP) {
            MAX_DEP = depth;
            res = root.val;
        }
        if (root.left != null) {
            dfs(root.left, depth + 1);
        }
        if (root.right != null) {
            dfs(root.right, depth + 1);
        }
    }
}
