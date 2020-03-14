package leetcode_inta.leetcode101_150;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/17
 * @describe 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 */
public class Q143reorderList {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //先尝试使用栈来实现
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode first = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时的slow为中间节点，开始保存后序需要反转的节点
        Stack<ListNode> stack = new Stack<>();
        ListNode slow_temp = slow;
        slow = slow.next;
        //断开连接
        slow_temp.next = null;
        while (slow != null) {
            stack.add(slow);
            slow = slow.next;
        }
        ListNode cur = first;
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            cur = cur.next;
            first.next = temp;
            temp.next = cur;
            first = cur;
        }
    }

    //发现大神没有用栈，但思路是和我一样的，不过他们是将后半段提前反转了
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时slow为中间节点
        //我们获取后半段转换之后的新链表的头节点
        ListNode right = reverse(slow.next);
        //用完中间节点功能，就把连接打断
        slow.next = null;
        //获取前半段的头节点
        ListNode left = head;

        //开始创建新的连接
        while (right != null) {
            //保留后半段节点的后一个节点
            ListNode right_next = right.next;
            //将后半段节点与前半段节点的后一个节点连接
            right.next = left.next;
            //这个时候，left.next原始作用更新了，可以被替换
            left.next = right;
            //移动left到最新位置
            left = left.next.next;
            //移动riht到最新位置
            right = right_next;
        }
    }
    //反转
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            //保留下一个
            ListNode next = head.next;
            //让下一个连接的方向转向
            head.next = pre;
            //移动pre到下一个准备转向的目的地
            pre = head;
            //起始点也移动
            head = next;
        }
        return pre;
    }
}
