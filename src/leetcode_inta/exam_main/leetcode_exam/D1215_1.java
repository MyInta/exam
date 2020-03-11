package leetcode_inta.exam_main.leetcode_exam;

/**
 * @author inta
 * @date 2019/12/15
 * @describe
 */
public class D1215_1 {
    private class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }
}
