package leetcode_inta.leetcode201_250;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/7
 * @describe 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Q234isPalindrome {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    public boolean isPalindrome(ListNode head) {
        ListNode count = head;
        Stack<ListNode> stack = new Stack<>();
        while (count != null) {
            stack.add(count);
            count = count.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //使用指针和反转链表实现
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode pre = null;
        ListNode pre_pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = pre_pre;
            pre_pre = pre;
        }
        //下半段开始位置必定为slow遍历到的下一个位置
        ListNode p2 = slow.next;
        //用过slow.next后，我们重新回归反转链表，将slow与pre建立连接
        slow.next = pre;
        //需要考虑的是反转之后的前半段链表起始位置
        ListNode p1 = slow;
        //考虑链表长度奇偶的不同
        if (fast == null) {
            //为null，说明长度为奇数，p1取slow前面一个所在位置
            p1 = pre;
        }
        //开始遍历p1和p2
        while (p1 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
