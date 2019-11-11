package tencent.leetcode151_200;

/**
 * @author inta
 * @date 2019/9/27
 * @describe 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 示例 1：
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
 * 链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 *
 * 示例 2：
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，
 * 链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal
 * 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *  
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 */
public class Q160getIntersectionNode {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
    //效率极低
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aCur = headA;
        ListNode bCur = headB;
        while (aCur != null && bCur != null) {
            if (aCur == bCur) {
                return aCur;
            } else {
                while (bCur != null) {
                    bCur = bCur.next;
                    if (aCur == bCur) {
                        return aCur;
                    }
                }
                bCur = headB;
                aCur = aCur.next;
            }
        }
        return null;
    }
    //取巧，指针遍历使得两链表于距离末端同一长度同时开始遍历，可以直接比较了
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode aTemp = headA;
        ListNode bTemp = headB;
        while (headA != headB) {
            if (headA == null && headB != null) {
                headA = bTemp;
                headB = headB.next;
            } else if (headB == null && headA != null) {
                headB = aTemp;
                headA = headA.next;
            } else {
                headA = headA.next;
                headB = headB.next;
            }
        }
        return headA;
    }
    //取巧，缩减写法
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode aTemp = headA, bTemp = headB;
        while (aTemp != bTemp) {
            aTemp = aTemp == null ? headB : aTemp.next;
            bTemp = bTemp == null ? headA : bTemp.next;
        }
        return aTemp;
    }
}