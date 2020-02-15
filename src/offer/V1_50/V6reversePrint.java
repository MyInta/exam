package offer.V1_50;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/2/14
 * @describe 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 */
public class V6reversePrint {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private List<Integer> list = new ArrayList<>();
    public int[] reversePrint(ListNode head) {
        //前序遍历，插入到list前面？
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i ++) {
            res[i] = list.get(list.size() - 1 - i);
        }
        return res;
    }
}
