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
    //思路，x节点后面的节点插入到x节点前一个节点后面即可
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        //这个节点用来记录x前一个节点
        ListNode pre = new ListNode(0);
        pre.next = head;
        //用来保存头信息，用以返回链表的时候，可以准确的返回第一个位置
        ListNode temp_start = pre;
        //用来记录当前节点
        ListNode cur = head;
        //遍历节点找到x节点
        while (cur != null && cur.val < x) {
            pre = pre.next;
            //当前节点后移
            cur = cur.next;
        }
        //用来记录需要插入的节点
        ListNode real_cur = cur;
        //跳出循环意味着找到x节点cur以及其前一个节点pre 或者是出现空指针的情况
        if (real_cur != null) {
            //现在场上有一个pre 他后面是x 然后我们要开始遍历x的后面
            real_cur = real_cur.next;
        }
        while (real_cur != null) {
            //当当前节点值小于目标值，开始插入操作
            if (real_cur.val < x) {
                cur.next = real_cur.next;
                real_cur.next = pre.next;
                pre.next = real_cur;
                //至此x前面的插入完成，我们需要进入下一个遍历
                pre = real_cur;
                real_cur = cur.next;
            } else {
                //如果是值大于x的，那么将cur移动，保留pre即可
                cur = cur.next;
                real_cur = cur.next;
            }
        }
        return temp_start.next;
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
