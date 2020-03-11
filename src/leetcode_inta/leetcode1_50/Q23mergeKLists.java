package leetcode_inta.leetcode1_50;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author inta
 * @date 2019/10/1
 * @describe 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class Q23mergeKLists {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null ||lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }
    //分治法
    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = ((right - left) >> 1) + left;
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);
        return mergeTwoLists(leftNode, rightNode);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next =  mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    private class Q23mergeKLists2{
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            PriorityQueue<ListNode> p = new PriorityQueue<>(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    return o1.val - o2.val;
                }
            });
            for (ListNode ln : lists) {
                if (ln != null) {
                    p.add(ln);
                }
            }
            while (!p.isEmpty()) {
                cur.next = p.poll();
                cur = cur.next;
                if (cur.next != null) {
                    p.add(cur.next);
                }
            }
            return dummy.next;
        }
    }
}
