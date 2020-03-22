import java.util.Arrays;

public class Main {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private class ListNode {
     int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }


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





    public static void main(String[] args) {
        int a = 1;
        a += Integer.MAX_VALUE - 1;
        System.out.println(a);
    }
}
