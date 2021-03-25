package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/18
 * @describe 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class Q83deleteDuplicates {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode temp = cur.next;
            if (cur.val == temp.val) {
                cur.next = temp.next;
            } else {
                cur = temp;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode next = head;
        while (next != null) {
            next = next.next;
            while (next != null && next.val == cur.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }
}
