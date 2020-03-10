package interview.I1_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/3/10
 * @describe 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表
 * （比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 */
public class I0403listOfDepth {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //bfs
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return null;
        List<ListNode> lists = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode ln = new ListNode(-1);
            //记录新建链表头信息，用于返回
            ListNode head = ln;
            for (int i = 0; i < size; i ++) {
                TreeNode temp = queue.poll();
                //建立连接
                ln.next = new ListNode(temp.val);
                //指针后移
                ln = ln.next;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            lists.add(head.next);
        }
        ListNode[] res = new ListNode[lists.size()];
        int index = 0;
        for (ListNode list : lists) {
            res[index ++] = list;
        }
        return res;
    }
}
