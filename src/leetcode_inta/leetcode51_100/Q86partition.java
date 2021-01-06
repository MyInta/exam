package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/12/2
 * @describe 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 */
public class Q86partition {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    // 题意理一理，就是把x节点后的小于x的节点放到x的前面，并保持初始相对位置
    public ListNode partition(ListNode head, int x) {
        // 假设的头
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // pre为到时候首次出现的大于等于x值的节点，用以插入定位
        ListNode pre = dummy;
        while (pre.next != null && pre.next.val < x) {
            pre = pre.next;
        }
        // 找出x节点前一节点，以及x所在节点cur
        ListNode cur = pre.next;
        // 开始遍历x节点后所有节点，按顺序插入到pre节点后即可
        while (cur != null && cur.next != null) {
            // 比较的下一节点
            ListNode curNext = cur.next;
            if (curNext.val < x) { // 小于x就插入到前面
                cur.next = curNext.next;
                curNext.next = pre.next;
                pre.next = curNext;
                // 然后要保持相对位置，pre移动
                pre = curNext;
            } else { // 大于等于x就正常往下遍历
                cur = curNext;
            }
        }
        return dummy.next;
    }

    public ListNode partition2(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode cur1 = dummy1;
        ListNode cur2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                head = head.next;
                cur1 = cur1.next;
            } else {
                cur2.next = head;
                head = head.next;
                cur2 = cur2.next;
            }
        }
        //把这前后两段合在一起
        cur1.next = dummy2.next;
        cur2.next = null;
        return dummy1.next;
    }
}
