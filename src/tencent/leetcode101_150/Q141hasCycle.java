package tencent.leetcode101_150;

/**
 * @author inta
 * @date 2019/9/25
 * @describe 看Q142更进一步的算法
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q141hasCycle {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        // if (head == null || head.next == null) {
        //     return false;
        // }
        // HashSet<ListNode> h = new HashSet<>();
        // ListNode cur = head;
        // while (cur.next != null) {
        //     h.add(cur);
        //     cur = cur.next;
        //     if (h.contains(cur)) {
        //         return true;
        //     }
        // }
        // return false;

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
