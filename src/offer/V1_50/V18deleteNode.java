package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/21
 * @describe 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *  
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 */
public class V18deleteNode {
      private class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

      public ListNode deleteNode(ListNode head, int val) {
        //辅助点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //两个指针，用来构建前后关系
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        //这里我担心如果是不存在的情况下，cur为空
        if (cur != null) pre.next = cur.next;
        return dummy.next;
      }

      //还有人用递归做的，非常简洁易懂
      public ListNode deleteNode2(ListNode head, int val) {
        if (head.val == val) return head.next;
        if (head.next != null) head.next = deleteNode2(head.next, val);
        return head;
      }
}
