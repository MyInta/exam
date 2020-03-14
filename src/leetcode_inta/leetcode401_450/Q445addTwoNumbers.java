package leetcode_inta.leetcode401_450;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/20
 * @describe 给定两个非空链表来代表两个非负整数。
 * 数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶:
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例:
 *
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 *
 */
public class Q445addTwoNumbers {
    private class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    //个人喜欢使用双栈，这种做法比较简单，但是栈空间操作费时
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.add(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.add(l2);
            l2 = l2.next;
        }
        ListNode cur = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int v = add;
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                v += stack1.pop().val + stack2.pop().val;
            } else if (!stack1.isEmpty() ) {
                v += stack1.pop().val;
            } else if (!stack2.isEmpty()) {
                v += stack2.pop().val;
            }
            add = v / 10;
            v = v % 10;
            //建立新结点，并且将其与之前结点连接,从后往前顺序连接
            ListNode temp = new ListNode(v);
            temp.next = cur;
            cur = temp;
        }
        //当仍存在进值时，前面加个元素
        if (add != 0) {
            ListNode temp = new ListNode(add);
            temp.next = cur;
            cur = temp;
        }
        return cur;
    }


    //大佬思路，填充空位为0
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l1_temp = l1;
        ListNode l2_temp = l2;
        //将两结点往后遍历，直到遇到至少一方为空
        while (l1_temp != null && l2_temp != null) {
            l1_temp = l1_temp.next;
            l2_temp = l2_temp.next;
        }
        //用来保留初始头位置
        ListNode pre = new ListNode(0);
        //保留相加之后的新链表
        ListNode res = new ListNode(0);
        //遇到空，开始从头添加0，并开始将两个结点相加
        if (l1_temp != null) {
            addZero(pre, l1_temp, l2);
            res = addTwoNumberMehtod(l1, pre.next, res);
        }
        if (l2_temp != null) {
            addZero(pre, l2_temp, l1);
            res = addTwoNumberMehtod(l2, pre.next, res);
        }
        //当两个链表长度相等的情况
        if (l1_temp == null && l2_temp == null) {
            res = addTwoNumberMehtod(l1, l2, res);
        }
        //如果头大于等于10，就裁剪一个小于十的元素，插入进来
        if (res.val >= 10) {
            ListNode newRes = new ListNode(res.val % 10);
            res.val = res.val / 10;
            newRes.next = res.next;
            res.next = newRes;
        }
        return res;
    }
    private void addZero(ListNode start, ListNode resume, ListNode dest) {
        //新设一个变量，这样就不用担心原始头位置变动了
        ListNode newStart = start;
        while (resume != null) {
            newStart.next = new ListNode(0);
            newStart = newStart.next;
            resume = resume.next;
        }
        newStart.next = dest;
    }
    private ListNode addTwoNumberMehtod(ListNode left, ListNode right, ListNode res) {
        if (left.next == null && right.next == null) {
            res.next = new ListNode(left.val + right.val);
            return res.next;
        }

        res.next = new ListNode(-1);
        res = res.next;
        //一直递归找到最后一位相加情况
        ListNode ln = addTwoNumberMehtod(left.next, right.next, res);
        //先把进位ln.val / 10用掉
        int temp = left.val + right.val + ln.val / 10;
        //最后一位取个位值
        ln.val = ln.val % 10;
        //倒着累加的第二个位置
        res.val = temp;
        return res;
    }
}
