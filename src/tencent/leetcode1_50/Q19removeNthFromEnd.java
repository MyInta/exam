package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/9
 * @describe 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 */
public class Q19removeNthFromEnd {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode target = pre;
        ListNode next = pre;
        while (n >= 0) {
            next = next.next;
            n --;
        }
        while (next != null) {
            target = target.next;
            next = next.next;
        }
        target.next = target.next.next;
        return pre.next;
    }
}
