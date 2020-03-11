package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/11
 * @describe 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 *
 * 示例:
 *
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 *
 */
public class I0204partition {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //就是说，把比x小的放到前面，比x大于等于的放到后面，保持相对位置即可
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                head = head.next;
                cur1 = cur1.next;
                cur1.next = null;
            } else {
                cur2.next = head;
                head = head.next;
                cur2 = cur2.next;
                cur2.next = null;
            }
        }
        //把这前后两段合在一起
        cur1.next = dummy2.next;
        return dummy1.next;
    }
}
