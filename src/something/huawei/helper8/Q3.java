package something.huawei.helper8;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author inta
 * @date 2021/12/23
 * @describe
 */
public class Q3 {
    private static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
        TreeNode(char x) { val = x; }
    }

    private static StringBuilder sb;
    public static String method(String str) {
        sb = new StringBuilder();
        if (str.length() == 0) {
            return "";
        }
        // 关键在于如何切割左右字符串，以形成新的左右两子树
        TreeNode tn = buildTreeNode(str);
        // 中序遍历输出结果即可
        inOrderTree(tn);
        return sb.toString();
    }

    private static void inOrderTree(TreeNode tn) {
        if (tn == null) {
            return;
        }
        inOrderTree(tn.left);
        sb.append(tn.val);
        inOrderTree(tn.right);
    }

    private static TreeNode buildTreeNode(String str) {
        TreeNode root = null;
        char[] chars = str.toCharArray();
        boolean isLeft = false;
        TreeNode curTreeNode = null;
        Deque<TreeNode> deque = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{') {
                isLeft = true;
                deque.push(curTreeNode);
            } else if (chars[i] == ',') {
                isLeft = false;
            } else if (chars[i] == '}') {
                isLeft = false;
                deque.pollFirst();
            } else {
                curTreeNode = new TreeNode(chars[i]);
                if (root == null) {
                    root = curTreeNode;
                } else {
                    TreeNode temp = deque.peek();
                    if (isLeft) {
                        temp.left = curTreeNode;
                    } else {
                        temp.right = curTreeNode;
                    }
                }
            }
        }
        return root;
    }

    // 经典的中序遍历，难点只在于输入输出的处理
    // a{b{d,e{g,h{,i}}},c{f}}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        System.out.println(method(inputStr));
    }
}
