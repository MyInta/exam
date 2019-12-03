package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/3
 * @describe 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class Q145postorderTraversal {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    //使用迭代,参考的评论区方法，有点繁琐难理解
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //使用栈保存父节点信息
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return res;
        //用来解决重复遍历的难点
        TreeNode solution = null;
        while (!stack.isEmpty() || root != null) {
            //当可以往左子树遍历时候，优先左子树
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.peek();
                //当root到左子树头，需要考虑其右子树，但难点在于右子树可能会重复遍历
                if (root.right != null && root.right != solution) {
                    root = root.right;
                } else {
                    //当左右两边都为null的时候，可以赋值了
                    res.add(root.val);
                    //难点来了，往上回溯的父节点，如何避免再次向下找重复的左右节点呢，用一个节点保存
                    stack.pop();
                    solution = root;
                    root = null;
                }
            }
        }
        return res;
    }


    //使用递归特比简单
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left != null) {
            dfs(root.left, list);
        }
        if (root.right != null) {
            dfs(root.right, list);
        }
        list.add(root.val);
    }

    //官方迭代 是将先序结果逆序出来即为后序,我们将添加元素顺序为父节点-右节点-左节点，这样逆序就是后序了
    public List<Integer> postorderTraversal3(TreeNode root) {
        //而且用的是linked链表，因为较多插入操作，而不考虑查询操作，用arrayList效率反而低
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        if (root == null) return res;
        while (!que.isEmpty()) {
            //找队列里面最早添加的元素
            TreeNode temp = que.pollFirst();
            //将结果保存在当前结果队列最前面，相当于结果逆序
            res.addFirst(temp.val);
            if (temp.left != null) {
                que.addFirst(temp.left);
            }
            if (temp.right != null) {
                que.addFirst(temp.right);
            }
        }
        return res;
    }
}
