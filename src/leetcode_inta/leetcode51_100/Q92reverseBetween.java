package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/20
 * @describe 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Q92reverseBetween {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        // 起始端点m前一个端点
        ListNode mln = pre;
        int temp_m = m;
        while (temp_m > 1) {
            mln = mln.next;
            temp_m --;
        }
        // 当前所在点
        ListNode cur = mln.next;
        // 当前点本来的下一个点
        ListNode next = cur.next;
        while (n > m && next != null) {
            ListNode next_next = next.next;
            next.next = cur;
            cur = next;
            next = next_next;
            n --;
        }
        // 出发点前面那个指向最后n后的节点(原先的n节点指向原先m节点后一节点)
        mln.next.next = next;
        // 更新出发点节点前节点连接反转到的末端cur指针
        mln.next = cur;

        return pre.next;
    }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 找到left节点的前一结点
        ListNode pre = dummy;
        int index = 1;
        while (index < left) {
            pre = pre.next;
            index++;
        }

        ListNode cur = pre.next;
        ListNode next = cur.next;
        while (index < right && next != null) {
            ListNode nextNext = next.next;
            next.next = cur;
            cur = next;
            next = nextNext;
            index++;
        }

        // 三个片段连接
        pre.next.next = next;
        pre.next = cur;
        return dummy.next;
    }
}
