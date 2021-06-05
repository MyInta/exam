package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/11/11
 * @describe
 */
public class Q203removeElements {

    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        dummy.next = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                continue;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // 递归
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 倒着找，从末端null往上遍历
        head.next = removeElements2(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }
}
