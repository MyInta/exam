package tencent.leetcode401_450;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/20
 * @describe 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 *
 *  
 *
 * 示例:
 *
 * 输入:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 *
 */
public class Q430flatten {
    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        //感觉最简单的方式就是栈保存后续结点
        Node dummy = new Node();
        dummy.next = head;
        Stack<Node> stack = new Stack<>();

        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                //当原先后续结点不为空，stack里保存
                if (cur.next != null) stack.push(cur.next);
                //指针滑向子双向链表,并建立连接
                cur.child.prev = cur;
                cur.next = cur.child;
                cur.child = null;
                cur = cur.next;
            } else {
                if (cur.next == null && !stack.isEmpty()) {
                    //取出上一支线，并将两节点建立连接
                    Node stackNode = stack.pop();
                    cur.next = stackNode;
                    stackNode.prev = cur;
                }
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
