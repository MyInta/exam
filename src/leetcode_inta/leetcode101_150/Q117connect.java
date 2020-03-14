package leetcode_inta.leetcode101_150;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2020/1/2
 * @describe 给定一个二叉树
 *
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *  
 *
 * 提示：
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *
 */
public class Q117connect {
    private class Node{
        int val;
        Node left;
        Node right;
        Node next;
        Node(){}
        Node(int val) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    //层次遍历，每层从右往左遍历，依次加next
    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node dummy = null;
            for (int i = 0; i < size; i ++) {
                Node temp = queue.poll();
                temp.next = dummy;
                dummy = temp;
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
            }
        }
        return root;
    }

    //大神的层次遍历法，使用了一个dummy记录每层开头和tail跟踪cur进行连接
    public Node connect2(Node root) {
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node();
            Node tail = dummy;
            //开始同层遍历
            while (cur != null) {
                if (cur.left != null) {
                    //使tail跟踪cur的子节点，并创建连接（最后位置没有连接，默认到null）
                    tail.next = cur.left;
                    //更新tail位置
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                //然后跟新cur位置
                cur = cur.next;
            }
            //如果出循环，说明一层遍历完了，需要进入下一层,这里dummy借助tail其next已经绑在子节点最左
            cur = dummy.next;
        }
        return root;
    }

    //另有大神给的比较常规的思路，虽然看起来复杂，但却是正常人的思考思路，就是实现上需要技术,效率最高
    public Node connect3(Node root) {
        if (root == null) return root;
        if (root.left != null) {
            if (root.right != null) {
                //左右无障碍，连通就是
                root.left.next = root.right;
            } else {
                //若右子节点不存在，就从父节点的邻居家找同龄人
                root.left.next = getNextNode(root.next);
            }
        }
        if (root.right != null) {
            //右子节点，我们只考虑邻居家的孩子就好了
            root.right.next = getNextNode(root.next);
        }
        //递归，对其孩子也实现孩子的孩子成为玩伴 注意这里要先给右边贯通，再贯通左边，不然中间有断层问题
        connect3(root.right);
        connect3(root.left);
        return root;
    }
    //给子节点找个牵右手的伴儿
    private Node getNextNode(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            return root.left;
        } else if (root.right != null) {
            return root.right;
        } else {
            //也有可能该节点虽然不为空但是没有子节点，我们需要找下一个结点
            return getNextNode(root.next);
        }
    }
}
