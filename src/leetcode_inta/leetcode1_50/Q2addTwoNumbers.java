package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/9/28
 * @describe 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 */
public class Q2addTwoNumbers {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode temp = res;
        //代表低位相加后递进的量，如5+7 递进1到moreNum中
        int moreNum = 0;
        while (l1 != null || l2 != null) {
            int i1 = (l1 != null) ? l1.val : 0;
            int i2 = (l2 != null) ? l2.val : 0;
            int sum = i1 + i2 + moreNum;
            moreNum = sum/10;
            res.next = new ListNode(sum%10);
            res = res.next;
            l1 = (l1 != null) ? l1.next : l1;
            l2 = (l2 != null) ? l2.next : l2;
        }
        if (moreNum > 0) {
            res.next = new ListNode(moreNum);
        }
        return temp.next;
    }
}
