package leetcode_inta.leetcode101_150;

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

    //依据剑指offer题35拷贝链表
        //网友推荐方法一：将要拷贝的信息存储在map中，然后再在map中依据原链表信息串联各自的结点
/*    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Map<Node, Node> hm = new HashMap<>();
        //一遍搭建所有原链表和新结点的关联map映射信息
        Node cur = head;
        while (cur != null) {
            hm.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //第二遍依据原链表中串联信息修改新结点的串联信息
        cur = head;
        while (cur != null) {
            hm.get(cur).next = hm.get(cur.next);
            hm.get(cur).random = hm.get(cur.random);
            //继续串联下一个
            cur = cur.next;
        }
        return hm.get(head);
    }*/

    //网友推荐方法二：在原链表每个结点后插入新结点，串联好后再将链表分割为奇偶，返回偶数链表就是我们求解
/*    public Node copyRandomList3(Node head) {
        if (head == null) return null;
        Node cur = head;
        //在每个结点后插入它的拷贝值
        while (cur != null) {
            //暂存下一个节点
            Node next = cur.next;
            //插入新结点
            cur.next = new Node(cur.val);
            //连通新结点和后一结点
            cur.next.next = next;
            //继续遍历下一个
            cur = next;
        }
        //依据前一结点将新结点的random信息串联好
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                //注意防止空指针，所有判null，新结点的random就是前一结点random的下一拷贝的新节点
                cur.next.random = cur.random.next;
            }
            //继续下一个新结点,这里默认cur.next非空，因为前面拷贝了双份长度
            cur = cur.next.next;
        }
        //拆分链表,编织新链表同时记得复原原先链表
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            //记录下原先链表下一个结点
            Node oldNext = cur.next.next;
            if (oldNext != null) {
                //串联新链表结点下一个信息
                cur.next.next = oldNext.next;
            }
            cur.next = oldNext;
            //移动到原有链表的下一结点，继续编织
            cur = oldNext;
        }
        return newHead;
    }*/
}
