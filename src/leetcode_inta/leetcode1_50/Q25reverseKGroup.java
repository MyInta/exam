package leetcode_inta.leetcode1_50;

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

    //使用的思路是递归，K范围内逆序，小于K就直接返回头，由原先保存的节点后K位置元素为新的遍历头，进入下一层递归
    public ListNode reverseKGroup2(ListNode head, int k) {
        //边界
        if (k <= 1 || head == null || head.next == null) return head;
        //遍历K-1位置之后可考虑成为返回对象
        ListNode temp = head;
        int k_copy = k;
        //遍历k-1个元素，看看长度够不够，够就继续，不够就返回原先头
        while (k_copy > 1) {
            temp = temp.next;
            //如果长度范围内，指针就已经到了空，说明长度不够
            if (temp == null) return head;
            k_copy --;
        }
        //此时没有返回的情况下，temp已经是新的头节点了，准备最后返回
        //保留预备递归用的头节点
        ListNode nextHead = temp.next;
        //逆序操作需要两个指针
        ListNode pre = head;
        ListNode cur = head.next;
        //重置长度
        k_copy = k;
        //因为不满足长度要求的都直接被返回了，这里就不用考虑空指针了吧
        while (k_copy > 1) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            k_copy --;
        }
        //此时完成了部分逆序，我们继续寻求下一段的逆序情况,并且注意此时head为逆序末尾
        head.next = reverseKGroup(nextHead, k);
        return temp;
    }
}
