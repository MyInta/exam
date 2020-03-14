package leetcode_inta.leetcode151_200;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/25
 * @describe 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * 示例：
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class Q173BSTIterator {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    PriorityQueue<Integer> pq;
    public Q173BSTIterator(TreeNode root) {
        pq = new PriorityQueue<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            pq.add(temp.val);
            if (temp.left != null) {
                stack.add(temp.left);
            }
            if (temp.right != null) {
                stack.add(temp.right);
            }
        }
    }

    /** @return the next smallest number */
    public int next() {
        return pq.remove();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !pq.isEmpty();
    }


    //大佬是将添加排序均摊到了每个next里面，牛逼
    Stack<TreeNode> stack;
    public void BSTIterator(TreeNode root) {
        stack = new Stack<>();
        if (root == null) return;
        stack.add(root);
        while (root.left != null) {
            root = root.left;
            stack.add(root);
        }
    }
    public int next2() {
        int next = -1;
        if (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            next = temp.val;
            if (temp.right != null) {
                temp = temp.right;
                stack.add(temp);
                while (temp.left != null) {
                    temp = temp.left;
                    stack.add(temp);
                }
            }
        }
        //题意说了不会为空，大胆删
        return next;
    }
    public boolean hasNext2() {
        return !stack.isEmpty();
    }
}
