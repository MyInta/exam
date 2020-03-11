package leetcode_inta.exam_main.leetcode_exam2020.D178;

/**
 * @author inta
 * @date 2020/3/1
 * @describe
 */
public class Q3 {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return isSubPath(head, root.left) || isSubPath(head, root.right) || find(head, root);
    }
    private boolean find(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) return false;
        return find(head.next, root.left) || find(head.next, root.right);
    }

}
