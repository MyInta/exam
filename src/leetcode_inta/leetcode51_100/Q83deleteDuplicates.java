package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/18
 * @describe 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class Q83deleteDuplicates {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    // 非递归体，考虑两两链表节点间的去重判断
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode temp = cur.next;
            if (cur.val == temp.val) {
                cur.next = temp.next;
            } else {
                cur = temp;
            }
        }
        return head;
    }

    // 非递归体，添加while判断，对同一值的链表节点一次去重
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode next = head;
        while (next != null) {
            next = next.next;
            while (next != null && next.val == cur.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }

    // 正向递归，考虑进入递归的下个链表节点和当前链表节点关系
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.val == head.val) {
            head.next = head.next.next;
            deleteDuplicates(head);
        } else {
            deleteDuplicates(head.next);
        }
        return head;
    }

    // 反向递归，一路递归到底后，层层回归，并考虑返回去重后的链表头
    public ListNode deleteDuplicates4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }
}
