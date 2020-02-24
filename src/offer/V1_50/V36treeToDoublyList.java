package offer.V1_50;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/24
 * @describe 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，
 * 树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 * 注意：本题与 426 题相同
 *
 */
public class V36treeToDoublyList {
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

    //递归 核心是中序遍历
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        build(root);
        //此时双向已经构建好,就缺循环了
        head.left = tail;
        tail.right = head;
        return head;
    }
    private Node head;
    private Node tail;
    private void build(Node root) {
        if (root == null) return;
        build(root.left);
        if (head == null) head = root;
        if (tail == null) tail = root;
        else {
            //如果tail已经存在,我们把它当作前驱点,来构建双向关系
            tail.right = root;
            root.left = tail;
            //移动指针到最新遍历的点,即下一个前驱点
            tail = root;
        }
        //考虑当前结点是否存在右节点,前驱结点需要的后结点,一定是在结点或结点的右树中最左结点
        build(root.right);
    }

    //迭代
    public Node treeToDoublyList2(Node root) {
        if (root == null) return null;
        Node head = null;
        Node tail = null;
        Stack<Node> stack = new Stack<>();
        Node temp = root;
        while (!stack.isEmpty() || temp != null) {
            while (temp != null) {
                stack.add(temp);
                temp = temp.left;
            }
            temp = stack.pop();
            if (head == null) {
                head = temp;
                tail = temp;
            } else {
                tail.right = temp;
                temp.left = tail;
                tail = temp;
            }
            temp = temp.right;
        }
        head.left = tail;
        tail.right = head;
        return head;
    }
}
