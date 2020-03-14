package leetcode_inta.leetcode551_600;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2019/11/12
 * @describe 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 */
public class Q589preorder {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    //递归做法
    private List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) return res;
        addVal(root);
        return res;
    }
    private void addVal(Node root) {
        if (root == null) return;
        res.add(root.val);
        for (Node n : root.children) {
            addVal(n);
        }
    }

    //使用非递归解答(压栈顺序别搞错了。。。。。。)
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            res.add(n.val);
            List<Node> childrens = n.children;
            for (int i = childrens.size() - 1; i >= 0; i --) {
                Node child = childrens.get(i);
                stack.add(child);
            }
        }
        return res;
    }


}
