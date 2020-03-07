package interview.I1_5;

/**
 * @author inta
 * @date 2020/3/7
 * @describe 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
 *
 *  
 *
 * 示例：
 *
 * 输入：单向链表a->b->c->d->e->f中的节点c
 * 结果：不返回任何数据，但该链表变为a->b->d->e->f
 *
 */
public class I0203deleteNode {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) return;
        ListNode next = node.next;
        while (next != null && next.next != null) {
            node.val = next.val;
            node = next;
            next = next.next;
        }
        //此时的node是倒数第二个结点
        node.next = null;
        node.val = next.val;
    }

    //我还是太罗嗦了，看了题解恍然大悟
    public void deleteNode2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
