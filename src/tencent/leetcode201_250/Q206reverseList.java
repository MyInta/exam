package tencent.leetcode201_250;

/**
 * @author inta
 * @date 2019/9/22
 * @describe 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class Q206reverseList {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {this.val =val;}
    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private class Q206reverseList2{
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            //p只是来记录最后找到的那个元素，也可以全局变量试试获得，用于返回一个最终头元素
            ListNode p = reverseList(head.next);
            //设定逆序方向上的链接
            head.next.next = head;
            //将原先顺序方向的链接断开
            head.next = null;
            return p;
        }
    }
}
