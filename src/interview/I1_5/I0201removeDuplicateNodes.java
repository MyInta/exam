package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/9
 * @describe 写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 *
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 */
public class I0201removeDuplicateNodes {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //最简单的实现就是设置一个数组储存出现过的结点值
    public ListNode removeDuplicateNodes(ListNode head) {
        int[] counts = new int[20001];
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (counts[cur.val] > 0) {
                //说明出现过
                pre.next = next;
                cur = next;
            } else {
                counts[cur.val] ++;
                pre = cur;
                cur = next;
            }
        }
        return dummy.next;
    }
}
