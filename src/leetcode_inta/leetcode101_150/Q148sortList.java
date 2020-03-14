package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/9/24
 * @describe 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class Q148sortList {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    //使用递归+归并排序思想求解
    public ListNode sortList(ListNode head) {
        //边界条件
        if (head == null || head.next == null) {
            return head;
        }
        //设立两个指针
        ListNode low = head;
        ListNode high = head.next;
        //low慢速后移，high快速后移，分别找到链表中间和末尾位置
        while (high != null && high.next != null) {
            low = low.next;
            high = high.next.next;
        }
        ListNode temp = low.next;
        //将链表截成两段
        low.next = null;
        //递归继续截取，直到返回单个元素
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        //遍历截取的两段内元素，互相比较同时排序
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                //并且left向右移动一位
                left = left.next;
            } else {
                cur.next = right;
                //同上，right向后移动一位
                right = right.next;
            }
            //每次拍好一个元素，将指针往后移动一位
            cur = cur.next;
        }
        //跳出while说明至少有一方遍历到了空，后续结点衔接另一方即可
        cur.next = left == null?right:left;
        //因为我们预先设置的头节点被保留在了pre里
        return pre.next;
    }
}
