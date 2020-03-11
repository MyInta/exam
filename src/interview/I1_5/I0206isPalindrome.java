package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/11
 * @describe 编写一个函数，检查输入的链表是否是回文的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 *
 * 输入： 1->2->2->1
 * 输出： true
 *  
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class I0206isPalindrome {

    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    //找到链表中间位置开始遍历即可 相当于pre之前是一段链表 cur之后是一段链表 两者相等即回文
    public boolean isPalindrome(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode fast = head;
        //遍历过程中构建链表将前半段反转
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //重点在于cur是取cur还是cur.next，依据于链表总长度的奇偶性,也就是fast在奇数长度时，一定非null，此时cur为中间值
        //我们要比较的是两段回文，即后半段和前半段，所以奇数情况下cur要进一步到cur.next
        if (fast != null) cur = cur.next;
        //此时的pre为中间位置，偶数时是中间靠左一个元素，奇数直接是中间位置，无论那种情况
        while (pre != null) {
            if (cur.val != pre.val) return false;
            cur = cur.next;
            pre = pre.next;
        }
        return true;
    }
}
