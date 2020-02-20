package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/20
 * @describe 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *  
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 */
public class V22getKthFromEnd {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size ++;
        }
        size -= k;
        while (size > 0) {
            head = head.next;
            size --;
        }
        return head;
    }
    //大佬都是双指针做的，有空补上
}
