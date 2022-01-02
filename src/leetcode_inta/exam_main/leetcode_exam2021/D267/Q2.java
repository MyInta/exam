package leetcode_inta.exam_main.leetcode_exam2021.D267;

import java.util.*;

/**
 * @author inta
 * @date 2021/11/14
 * @describe
 */
public class Q2 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 按照1、2、3... ... n的模块划分节点，最后一个模块可以不满足n个，将其中偶数段反转，最暴力方式，使用栈保存，最终重新构造
    public ListNode reverseEvenLengthGroups(ListNode head) {
        Deque<List<Integer>> queue = new LinkedList<>();
        int cur = 1;
        ListNode curNode = head;
        while (curNode != null) {
            int k = cur;
            List<Integer> curList = new ArrayList<>();
            while (curNode != null && k > 0) {
                curList.add(curNode.val);
                curNode = curNode.next;
                k--;
            }
            queue.push(curList);
            cur++;
        }
        // 重新构建node
        ListNode dummy = new ListNode(-1);
        ListNode nextNode = dummy;
        while (!queue.isEmpty()) {
            List<Integer> temp = queue.pollLast();
            if (temp.size() % 2 == 1) {
                for (int val : temp) {
                    nextNode.next = new ListNode(val);
                    nextNode = nextNode.next;
                }
            } else {
                for (int i = temp.size() - 1; i >= 0; i--) {
                    nextNode.next = new ListNode(temp.get(i));
                    nextNode = nextNode.next;
                }
            }
        }
        return dummy.next;
    }
}
