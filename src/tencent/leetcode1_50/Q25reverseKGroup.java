package tencent.leetcode1_50;

/**
 * @author inta
 * @date 2019/11/25
 * @describe 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Q25reverseKGroup {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    //递归
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;
        ListNode temp = head;
        int k_temp = k;
        while (k_temp > 1) {
            //当长度在k以下的时候，直接返回头节点即可
            if (temp == null || temp.next == null) {
                return head;
            }
            temp = temp.next;
            k_temp --;
        }
        k_temp = k;
        //用来递归时候使用
        ListNode temp_next = temp.next;

        //开始反转，并且给下一截继续递归
        ListNode cur_pre = head;
        ListNode cur = head.next;
        ListNode cur_next = cur.next;
        while (k_temp > 1) {
            cur.next = cur_pre;
            cur_pre = cur;
            cur = cur_next;
            if (cur == null) {
                break;
            }
            cur_next = cur_next.next;
            k_temp --;
        }

        head.next = reverseKGroup(temp_next, k);

        return temp;
    }
}
