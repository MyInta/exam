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



    /**
     * 校验原字符串 s 是否匹配 规则 p
     * @param s string字符串 原字符串
     * @param p string字符串 匹配规则
     * @return bool布尔型
     */
    public boolean Match (String s, String p) {
        if (p == null || s == null) return false;
        int index = 0, p_index = 0;
        while (index < s.length()) {
            //如果字符匹配，就遍历下一个
            if (s.charAt(index) == p.charAt(p_index) || p.charAt(p_index) == '.') {
                index ++;
                p_index ++;
            } else if (p.charAt(p_index) == '(') {
                int end = p_index + 1;
                while (p.charAt(end) != ')') {
                    end ++;
                }
                //截取出字符集
                String cutStr = p.substring(p_index + 1, end);
                //如果是一个字符集加*的组合
                if (end < p.length() - 1 && p.charAt(end + 1) == '*') {
                    while (index < s.length() && isValid(s.substring(index, index + cutStr.length()), cutStr)) {
                        //如果和p的字符集匹配，索引就往后移动
                        index += cutStr.length();
                    }
                    //此时的s匹配完了，我们把p往后移动到*之后
                    p_index += cutStr.length() + 3;
                } else {
                    if (!isValid(s.substring(index, index + cutStr.length()) ,cutStr)) return false;
                    index += cutStr.length();
                    p_index += cutStr.length() + 2;
                }
            } else {
                return false;
            }
        }
        return p_index == p.length() && index == s.length();
    }
    private boolean isValid(String str, String match) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != match.charAt(i) && match.charAt(i) != '.') return false;
        }
        return true;
    }



    public static void main(String[] args) {
        int a = Integer.MIN_VALUE;
        System.out.println(a);
        a --;
        System.out.println(a);
        a ++;
        System.out.println(a);
    }
}
