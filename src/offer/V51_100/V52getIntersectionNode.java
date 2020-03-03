package offer.V51_100;

/**
 * @author inta
 * @date 2020/3/3
 * @describe 输入两个链表，找出它们的第一个公共节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
 * 链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 *
 * 示例 2：
 *
 *
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，
 * 链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 *
 * 示例 3：
 *
 *
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，
 * 所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *  
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *  LC160
 */
public class V52getIntersectionNode {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //一遍遍历找A长度，一遍找B长度，然后对齐后比较是否相交
        int lenA = 0, lenB = 0;
        ListNode tempA = headA;
        while (tempA != null) {
            lenA ++;
            tempA = tempA.next;
        }
            ListNode tempB = headB;
        while (tempB != null) {
            lenB ++;
            tempB = tempB.next;
        }
        //对齐
        while (lenA > lenB) {
            headA = headA.next;
            lenA --;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB --;
        }
        //对齐之后，我们挨个比较
        while (lenA > 0) {
            if (headA == headB) return headA;
            lenA --;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //双指针法，两指针挨个找链表元素，到尽头后就切换到另一个表头
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            //如果到尽头，就切换到另一个链表头，否则就继续往后遍历
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
            //因为如果链表长度一样，就算不相交，随后都是null会跳出循环；
            // 如果长度不一样，总能移动到两者位置都为null的瞬间跳出，同理可以移动到两者相等位置；
            // 因为移动速度一样，不用担心会在同一个链表位置碰撞
        }
        return curA;
    }
}
