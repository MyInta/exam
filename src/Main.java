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


//    int countCharacters(char[] chars, String[] words) {
//        int[] counts = new int[128];
//        for (char c : chars) {
//            counts[c] ++;
//        }
//        int res = 0;
//        for (String word : words) {
//            int[] temp = Arrays.copyOf(counts, counts.length);
//            if (valid(word, temp)) res += word.length();
//        }
//        return res;
//    }
//    private boolean valid(String str, int[] counts) {
//        for (char c : str.toCharArray()) {
//            if (counts[c] == 0) return false;
//            counts[c] --;
//        }
//        return true;
//    }


    public static void main(String[] args) {

    }
}
