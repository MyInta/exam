package leetcode_inta.leetcode51_100;

/**
 * @author inta
 * @date 2019/11/21
 * @describe 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 */
public class Q82deleteDuplicates {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    //自己理解的非递归，用了boolean，看大神是可以不用的，需要加个while循环
    public ListNode deleteDuplicates(ListNode head) {
        //可以省去这个判断
        //        if (head == null || head.next == null) return head;
        //前面放一个假肢
        ListNode pre = new ListNode(0);
        //它联通头节点
        pre.next = head;
        //给一个遍历的当前节点
        ListNode cur = pre;
        //真正干活的后面员工
        ListNode first = cur.next;
        //标识员工干活过程有没有重复值出现
        boolean dup = false;
        while (first != null) {
            //当员工后面遇到尽头了，我们让领导直接指向空返回即可
            if (first.next == null) {
                //当遇到不一样的值，我们换个员工，并且领导指导下新的员工,如果非重复，领导下放
                if (!dup) {
                    cur = first;
                }
                cur.next = null;
                break;
            } else {
                //当员工值遇到重复了，只移动员工,标识下这是重复的
                if (first.val == first.next.val) {
                    dup = true;
                    first = first.next;
                } else {
                    //当遇到不一样的值，我们换个员工，并且领导指导下新的员工,如果非重复，领导下放
                    if (!dup) {
                        cur = first;
                    }
                    first = first.next;
                    cur.next = first;
                    //每次遇到不同，我们重新考虑是否重复，默认不重复
                    dup = false;
                }
            }
        }
        return pre.next;
    }

    //若使用递归
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return head;
        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
        }
        return head;
    }

    //非递归
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode slow = pre;
        ListNode fast = pre.next;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;
            }
            //当slow与fast不相等时，说明fast迁移过了
            if (slow.next != fast) {
                //那么将slow的指针指向fast的下一个位置而不移动slow即可
                slow.next = fast.next;
//                fast = fast.next;
            } else {
                //说明fast没有遇到重复的，要取值，slow移位
                slow = fast;
//                fast = fast.next;
            }
            fast = fast.next;
        }
        return pre.next;
    }
}
