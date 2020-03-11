package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/10/1
 * @describe 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Q21mergeTwoLists {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                break;
            }
            if (l2 == null) {
                cur.next = l1;
                break;
            }
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return res.next;
    }

    //使用递归解法代码简洁一些
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
