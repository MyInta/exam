package leetcode_inta.leetcode1001_1050;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/6/13
 * @describe 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 *
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i)
 * 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。
 * 如果不存在这样的 j，那么下一个更大值为 0 。
 *
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 *
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，
 * 其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 *
 * 示例 2：
 *
 * 输入：[2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 *
 * 示例 3：
 *
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 *
 *
 *
 * 提示：
 *
 *     对于链表中的每个节点，1 <= node.val <= 10^9
 *     给定列表的长度在 [0, 10000] 范围内
 *
 */
public class Q1019nextLargerNodes {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //暴力解
    public int[] nextLargerNodes(ListNode head) {
        int size = 0;
        ListNode temp = head;
        //获取链表长度
        while (temp != null) {
            temp = temp.next;
            size ++;
        }
        int[] res = new int[size];
        temp = head;
        for (int i = 0; i < size - 1; i++) {
            ListNode cur = temp.next;
            while (cur != null && cur.val <= temp.val) {
                cur = cur.next;
            }
            res[i] = cur == null ? 0 : cur.val;
            temp = temp.next;
        }
        return res;
    }

    //使用数组保存索引和值，并用单调栈
    // 举例描述单调栈的行为 9 7 4 3 2 5 中保存9 7 4 3 2 获得5比2大，栈中去掉2，继续比较-->9 7 4 3 5
    //                                      9 7 4 3 --> 9 7 4 -->此时5比7小，5入栈--> 9 7 5
    public int[] nextLargerNodes2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        //记录原先每个索引位置对应的值
        List<Integer> value = new ArrayList<>();
        //最终对应位置记录值
        List<Integer> res = new ArrayList<>();
        //记录遍历的list的索引
        int index = 0;
        while (head != null) {
            value.add(head.val);
            //如果该位置没有在往后遇到较大值，默认情况都是0
            res.add(0);
            //因为stack保存的是原先索引对应的值，所以我们通过栈中出栈索引值可以找到真实值
            while (!stack.isEmpty() && head.val > value.get(stack.peek())) {
                //修改最终位置对应值,索引->真实值
                res.set(stack.pop(), head.val);
            }
            stack.add(index ++);
            head = head.next;
        }
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    //网友对上面做了精修
    public int[] nextLargerNodes3(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        int size = 0;
        while (head != null) {
            list.add(head.val);
            size ++;
            head = head.next;
        }
        int[] res = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            //如果栈不为空，并且从右往左比较的时候，比较元素比栈顶元素要大，
            // 就删去栈顶元素，因为再往左遍历，第一个遇到的最大的也不会是它
            while (!stack.isEmpty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }
            //确认下结果集，如果栈比较下来，没有元素比它大，那该位置就是0，否则取元素放该位置
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            //目前元素不管大小，都要加入栈中，可能再往左的元素比它小呢
            stack.push(list.get(i));
        }
        return res;
    }
}
