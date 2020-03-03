package offer.V51_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/3/3
 * @describe 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，
 * 每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *  
 *
 * 提示：
 *
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 *  LC138
 */
public class V35copyRandomList {
    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //递归做法，需要考虑重复操作，理解有难度
    private Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node dummy = new Node(head.val);
        if (map.containsKey(head)) {
            return map.get(head);
        } else {
            //如果拷贝信息还没有储存，就往map中新建
            map.put(head, dummy);
        }
        dummy.next = copyRandomList(head.next);
        dummy.random = copyRandomList(head.random);
        return dummy;
    }

    //网友推荐方法一：将要拷贝的信息存储在map中，然后再在map中依据原链表信息串联各自的结点
    public Node copyRandomList2(Node head) {
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
    }

    //网友推荐方法二：在原链表每个结点后插入新结点，串联好后再将链表分割为奇偶，返回偶数链表就是我们求解
    public Node copyRandomList3(Node head) {
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
    }
}