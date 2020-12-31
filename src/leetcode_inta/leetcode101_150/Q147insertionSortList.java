package leetcode_inta.leetcode101_150;

/**
 * @author inta
 * @date 2019/12/30
 * @describe 对链表进行插入排序。
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class Q147insertionSortList {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        //前置结点
        ListNode pre = new ListNode(-1);
        pre.next = head;
        //保留原有节点位置
        ListNode new_head = pre;
        //可能的需要插入的结点
        ListNode cur = head.next;
        while (cur != null) {
            //先保留下cur下一个结点位置
            ListNode cur_next = cur.next;
            //当遍历的结点值小于前面的节点值时候，需要插入操作
            if (head.val > cur.val) {
                //需要提出插入的元素，把原先插入元素前后位置连通
                head.next = cur_next;
                //连通前面的，在pre和head之间安插
                //为保持升序,需要找到前面第一个比cur大的结点前一个结点
                //更新开头的位置
                new_head = pre;
                while (new_head.next != null && new_head.next.val <= cur.val) {
                    //遍历找大小不同的位置
                    new_head = new_head.next;
                }
                //此时的new_head为我们所求点,因为前提if存在，则证明pre.next不为null
                //开始插入操作，先储存后一个结点
                ListNode pre_next = new_head.next;
                //建立连接
                new_head.next = cur;
                cur.next = pre_next;
            } else {
                //如果非逆序，就正常找下一个节点看看
                head = head.next;
            }
            cur = cur_next;
        }
        //返回头节点
        return pre.next;
    }

    //重头写一遍精简点的，加深下记忆
    public ListNode insertionSortList2(ListNode head) {
        ListNode dummpy = new ListNode(Integer.MIN_VALUE), pre = dummpy;
        dummpy.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            //如果逆序，就要从头寻找合适位置插入
            pre = dummpy;
            while (pre.next.val <= head.next.val) {
                pre = pre.next;
            }
            //pre此时为需要插入结点左侧位置,先把结点（该节点是head.next）提出来并建立连接
            ListNode head_next = head.next;
            head.next = head_next.next;
            //给前面也建立连接
            head_next.next = pre.next;
            pre.next = head_next;
        }
        return dummpy.next;
    }

    //2020.11.20又一次实现，基本思想没变，还是之前总结的比较精炼
    public ListNode insertionSortList3(ListNode head) {
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode dumpty = pre;
        ListNode cur = head;
        while (cur != null) {
            if (pre.val > cur.val) {
                //每次从头开始找位置
                ListNode temp_pre = dumpty;
                ListNode temp_cur = dumpty.next;
                while (temp_cur.val <= cur.val && temp_cur != cur) {
                    temp_pre = temp_cur;
                    temp_cur = temp_cur.next;
                }
                //如果前半段都小于目标值，目标值不用改变位置，否则的话，就是插入操作
                if (temp_cur.val > cur.val) {
                    //先切断原先节点前后关系
                    pre.next = cur.next;
                    //再进行插入操作
                    temp_pre.next = cur;
                    cur.next = temp_cur;
                }
            }
            pre = cur;
            cur = cur.next;
        }
        return dumpty.next;
    }
}
