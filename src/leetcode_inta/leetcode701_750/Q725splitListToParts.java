package leetcode_inta.leetcode701_750;

/**
 * @author inta
 * @date 2020/6/6
 * @describe 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 *
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 *
 * 返回一个符合上述规则的链表的列表。
 *
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 *
 * 示例 1：
 *
 * 输入:
 * root = [1, 2, 3], k = 5
 * 输出: [[1],[2],[3],[],[]]
 * 解释:
 * 输入输出各部分都应该是链表，而不是数组。
 * 例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.next.next.next = null。
 * 第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * 最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 *
 * 示例 2：
 *
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 *
 *
 *
 * 提示:
 *
 *     root 的长度范围： [0, 1000].
 *     输入的每个节点的大小范围：[0, 999].
 *     k 的取值范围： [1, 50].
 *
 */
public class Q725splitListToParts {
    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //按照k份除之后，剩余的数量从左到右添加1位即可
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode temp = root;
        int size = 0;
        while (temp != null) {
            size ++;
            temp = temp.next;
        }
        //此时获得root的链表总长度size,设resume为剩余需要添加的元素
        int resume = size % k;
        //num为基本每段长度，部分比其长1
        int num = size / k;
        ListNode[] res = new ListNode[k];
        //结果集的索引
        int index = 0;
        ListNode cur = root;
        //前resume每段长度为num+1
        for (; index < resume; index++) {
            res[index] = cur;
            for (int j = 0; j < num; j++) {
                cur = cur.next;
            }
            //此时cur为该段最后一个节点，我们现在要把它断开，又想要它的下一个，所以设个next
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        for (; index < k; index++) {
            res[index] = cur;
            //需要考虑，是否为空，也就是分段落数量大于元素数量了
            for (int j = 0; j < num - 1 && cur != null; j++) {
                cur = cur.next;
            }
            //此时cur为该段最后一个节点，我们现在要把它断开，又想要它的下一个，所以设个next
            ListNode next = null;
            //同上考虑是否是空段落
            if (cur != null) {
                next = cur.next;
                cur.next = null;
            }
            cur = next;
        }
        return res;
    }
}
