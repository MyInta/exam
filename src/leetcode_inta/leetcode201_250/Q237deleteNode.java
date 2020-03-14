package leetcode_inta.leetcode201_250;

/**
 * @author inta
 * @date 2019/9/23
 * @describe 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], node = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], node = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 */
public class Q237deleteNode {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    //就是把下一个结点的值拷到该结点上，并删除下一个结点即可
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
