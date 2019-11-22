package tencent.leetcode801_850;

import java.util.*;

/**
 * @author inta
 * @date 2019/11/22
 * @describe 给定一个链表（链表结点包含一个整型值）的头结点 head。
 *
 * 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 *
 * 示例 1：
 *
 * 输入:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * 输出: 2
 * 解释:
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 *
 * 输入:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * 输出: 2
 * 解释:
 * 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 * 注意:
 *
 * 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
 * 链表中每个结点的值所在范围为 [0, N - 1]。
 * 1 <= G.length <= 10000
 * G 是链表中所有结点的值的一个子集. 里面元素值不重复
 *
 */
public class Q817numComponents {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    //自己的解法，是思考将每个节点的索引重新编码，然后照着排序找缺损排序段段落有几个
    public int numComponents(ListNode head, int[] G) {
        if (G == null || G.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        while (head != null) {
            map.put(head.val, index ++);
            head = head.next;
        }
        for (int i = 0; i < G.length; i ++) {
            G[i] = map.get(G[i]);
        }
        Arrays.sort(G);
        int temp = G[0];
        int res = 0;
        for (int g : G) {
            if (g - temp != 1) {
                res++;
            }
            temp = g;
        }
        return res;
    }

    //官解用set保存G集合，遍历链表来查询非连续子序列数量
    public int numComponents2(ListNode head, int[] G) {
        Set<Integer> g_set = new HashSet<>();
        for (int g : G) {
            g_set.add(g);
        }
        int res = 0;
        while (head != null) {
            //当发现一个下一值没有出现在G集合中，即“段落式”的序列时，添加数量
            if (g_set.contains(head.val) && (head.next == null || !g_set.contains(head.next.val))) {
                res ++;
            }
            //head是往后一直延伸的
            head = head.next;
        }
        return res;
    }
}
