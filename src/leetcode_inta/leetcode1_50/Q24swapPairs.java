package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/11/23
 * @describe 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 */
public class Q24swapPairs {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next;
        ListNode slow = head;
        ListNode pre = fast;
        ListNode temp = slow;
        while (temp.next != null) {
            temp = fast.next;
            fast.next = slow;
            if (temp == null) {
                slow.next = null;
                break;
            }
            if (temp.next == null) {
                slow.next = temp;
                break;
            }
            slow.next = temp.next;
            slow = temp;
            fast = temp.next;
        }
        return pre;
    }

    //递归
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode next = head.next;
        //本来是想着pre.next = next.next 但是要考虑后面第3.4两个也需要交换，走递归路线
        pre.next = swapPairs2(next.next);
        //只是把后面的链接创建了，还没有把前面的链接创建,修复下
        next.next = pre;
        return next;
    }
    //非递归 精简 画个图就出来了 pre-1-2-3-4  -> pre-2-1-3-4 -> pre移到1上 继续 pre-3-4
    public ListNode swapPairs3(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = pre;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            cur.next = second;
            first.next = second.next;
            second.next = first;
            cur = first;
        }
        return pre.next;
    }
}
