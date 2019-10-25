package tencent.leetcode101_150;

import java.util.HashMap;

/**
 * @author inta
 * @date 2019/10/25
 * @describe 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的深拷贝。
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *  
 * 提示：
 *
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 */
public class Q138copyRandomList {
    private class Node{
        int val;
        Node next;
        Node random;
        Node () {
        }
        Node(int _val, Node _next, Node _random) {
            this.val = _val;
            this.next = _next;
            this.random = _random;
        }
    }
    private HashMap<Node, Node> hm = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node newNode = new Node(head.val, null, null);
        if (hm.containsKey(head)) {
            return hm.get(head);
        } else {
            hm.put(head, newNode);
        }
        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }
}
