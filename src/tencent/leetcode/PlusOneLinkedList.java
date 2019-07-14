package tencent.leetcode;


/**
 * @author inta
 * @date 2019/6/27
 * @describe 给单链表single linked添加整数 如 Input:9->9 Output:1->0->0
 *                             Input:1->9->8->9 Output:1->9->9->0
 *                             Input:1->9->9->9 Output:2->0->0->0
 */
public class PlusOneLinkedList {
    //创建ListNode的类
    private static class ListNode{
        int val;
        ListNode next;
        //construct
        ListNode(int x){
            this.val = x;
        }
    }
    /**
     * 给单链表添加1
     * @return 添加后的结果
     */
    private static ListNode plusOne(ListNode head){
        //考虑999前面设个位置dummy Node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //因为单链表不能返回，所以用双索引
        ListNode i = dummy;
        ListNode j = dummy;
        while(j.next!=null){//当节点下面不为空时遍历
            j = j.next;
            if(j.val!=9){
                i = j;//把i的索引放到最靠近末端的9前
            }
        }
        if(j.val!=9){
            j.val++;
        }else{//如果j为9，那么到i的距离内都需要添加1成为0
            i.val++;
            i = i.next;
            while(i!=null){
                i.val = 0;
                i = i.next;
            }
        }
        if(dummy.val == 0){
            return dummy.next;
        }
        return dummy;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode result = plusOne(l1);
        while(result!= null){
            System.out.print(result.val);
            result = result.next;
            if(result!=null){
                System.out.print("->");
            }
        }
    }

}
