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
        if (head == null) return null;
        while (head != null && head.val == val) head = head.next;
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    //递归
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;
//        if (head.val == val) return removeElements(head.next, val);
//        ListNode pre = head;
//        while (head.next != null) {
//            if (head.next.val == val) {
//                head.next = head.next.next;
//            } else {
//                head = head.next;
//            }
//        }
//        return pre;
        //倒着找，从末端null往上遍历
        head.next = removeElements2(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }
}
