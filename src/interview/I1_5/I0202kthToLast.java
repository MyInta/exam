package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/7
 * @describe 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 *
 * 给定的 k 保证是有效的。
 *
 */
public class I0202kthToLast {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public int kthToLast(ListNode head, int k) {
        //设一个快指针与head保持k个距离，fast为null时，head即为所求
        ListNode fast = head;
        while (k > 0) {
            fast = fast.next;
            k --;
        }
        while (fast != null) {
            head = head.next;
            fast = fast.next;
        }
        return head.val;
    }
}
