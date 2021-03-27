package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/9/27
 * @describe 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class Q61rotateRight {
    private class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        ListNode end = head;
        while (cur != null) {
            len++;
            if (cur.next == null) {
                // 记录下链表最后一个非空结点
                end = cur;
            }
            cur = cur.next;
        }
        if (k%len == 0) {
            return head;
        }
        int start = len - k%len;
        cur = head;
        start--;
        while (start > 0) {
            // 找到需要截断的位置，该结点后面接null
            cur = cur.next;
            start--;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        end.next = head;
        return newHead;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        // 获取原有链表长度
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }

        k %= size;
        // pre获取原末尾节点的前k个节点，tail获取末尾节点
        ListNode pre = head;
        ListNode tail = head;
        while (k > 0) {
            tail = tail.next;
            k--;
        }

        // 将tail推至末尾，由于前面while始得其与pre间距k，同时获得末尾节点前k的节点pre
        while (tail.next != null) {
            pre = pre.next;
            tail = tail.next;
        }

        // 下面的过程就是将末尾节点和原头节点连接，将pre.next也就是需要反转的节点作为新的头结点，并且打断原先pre到新头结点的连接
        tail.next = head;
        ListNode newHead = pre.next;
        pre.next = null;
        return newHead;
    }
}
