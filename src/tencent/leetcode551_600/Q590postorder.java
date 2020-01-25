package tencent.leetcode551_600;

import java.util.*;

/**
 * @author inta
 * @date 2020/1/23
 * @describe 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其后序遍历: [5,6,3,2,4,1].
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 */
public class Q590postorder {
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    //先递归
    private List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if (root == null) return res;
        if (root.children != null) {
            for (Node child : root.children) {
                postorder(child);
            }
        }
        res.add(root.val);
        return res;
    }
    //再迭代
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<>();
        if (root == null) return res;
        stack.add(root);
        while (!stack.isEmpty()) {
            Node temp = stack.peek();
            if (temp.children == null) {
                res.add(stack.pop().val);
            } else {
                stack.addAll(0, temp.children);
                //将添加完子结点的父节点子类引用改为null，可以节省逻辑复杂，但其实破坏原有结构了
                temp.children = null;
            }
        }
        return res;
    }
    //不破坏原有结构的迭代方式 大神使用了一个指针来记录前一个遍历时候的情况，如果是遍历过的就开始添加
    public List<Integer> postorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        //保存前一个遍历的结点信息
        Node pre = null;
        //遍历结点
        Stack<Node> stack = new Stack<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            //注意是peek，不能妄自删除
            Node cur = stack.peek();
            //如果子结点无 或者是遍历过的结点，开始添加
            if (cur.children.size() == 0 || (pre != null && cur.children.contains(pre))) {
                res.add(stack.pop().val);
                //更新pre
                pre = cur;
            } else {
                //否则，就按逆序添加到栈中
                for (int i = cur.children.size() - 1; i >= 0; i --) {
                    stack.add(cur.children.get(i));
                }
            }
        }
        return res;
    }

    //当然还有大佬提供的一个很轻奇的思路，就是在添加结果的时候，按照先序根 左 右 变成 添加左 右 根即可
}
