package tencent.leetcode;

/**
 * @author inta
 * @date 2019/8/30
 * @describe 搭建一个单链表，并实现反转和部分反转功能
 */
public class SingleNodeReverse {
    /**
     * 单链表
     */
    private static class SingleNode{
        int val;
        private SingleNode next;
        SingleNode(int val){
            this.val = val;
        }
        public void setNext(SingleNode next){
            this.next = next;
        }
        public SingleNode getNext(){
            return next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    /**
     * 实现链表的全反转
     * @param head 输入原先链表的头节点
     * @return 返回新反转之后的链表的头节点
     */
    public SingleNode reverseAll(SingleNode head){
        //如果头节点为空或者没有往下的节点了，直接返回节点对象
        if(head==null||head.getNext()==null){
            return head;
        }
        //获得链表需要反转的最后一个节点
        SingleNode end = reverseAll(head.getNext());
        //将节点下一个节点的下一个链接到节点本身
        head.getNext().setNext(head);
        //并且破坏掉原先的链接
        head.setNext(null);
        //因为是递归，每次递归一轮后head都会往上一个节点挪
        //当全部反转后，当初获得的最后一个节点即为新得头节点
        return end;
    }

    public void reversePart(SingleNode head,int start,int end){
        if(head==null||head.getNext()==null){
            return;
        }
        SingleNode cur = head;//当前节点
        for(int i=0;i<start-1;i++){
            head = cur;
            cur = cur.getNext();
        }
        SingleNode pre = cur;//上一节点
        cur = cur.getNext();
        SingleNode temp;

        for(int i = start-1;i<end-1;i++){
            temp=cur.next;
            cur.next=head.next;
            head.next=cur;
            pre.next=temp;
            cur=temp;
        }

    }


    public static void main(String[] args) {
        SingleNode singleNode1 = new SingleNode(1);
        SingleNode singleNode2 = new SingleNode(2);
        SingleNode singleNode3 = new SingleNode(3);
        SingleNode singleNode4 = new SingleNode(4);
        SingleNode singleNode5 = new SingleNode(5);
        SingleNode singleNode6 = new SingleNode(6);
        SingleNode singleNode7 = new SingleNode(7);
        singleNode1.setNext(singleNode2);
        singleNode2.setNext(singleNode3);
        singleNode3.setNext(singleNode4);
        singleNode4.setNext(singleNode5);
        singleNode5.setNext(singleNode6);
        singleNode6.setNext(singleNode7);

        SingleNode head;
        SingleNodeReverse snr = new SingleNodeReverse();
//        head = snr.reverseAll(singleNode1);
        snr.reversePart(singleNode1,2,7);
        head = singleNode1;
        while(head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
    }

}
