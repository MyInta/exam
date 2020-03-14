package leetcode_inta.leetcode101_150;

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

    //看了大神的解答，通过讲解使用set保存已遍历过的元素，层层深入进行诠释，最后使用一个结点保存上一访问结点信息
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        //后续遍历，不要提前push root进栈，不方便提取
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                //视察下栈顶元素，不要取出来
                TreeNode cur = stack.peek();
                //如果左节点为空，就考虑右节点和是否遍历过
                if (cur.right != null && cur.right != pre) {
                    //若右节点存在且不是访问过的，指针直到该位置
                    root = cur.right;
                } else {
                    //否则，可能是左右子节点都没了或者是访问过的，都需要从栈中剔除
                    stack.pop();
                    //添加该元素
                    res.add(cur.val);
                    //将跟踪用的结点pre刷新
                    pre = cur;
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

    //还有一种思想，代码看起来比较简洁，使用双次push，来保留根，这样可以同前序一样从栈中放心pop了
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            //同前序类似，考虑子节点，若和根节点同，说明在左子树，不同，则在右子树需要添加了
            TreeNode cur = stack.pop();
            if (!stack.isEmpty() && cur == stack.peek()) {
                if (cur.right != null) {
                    stack.push(cur.right);
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                    stack.push(cur.left);
                }
            } else {
                res.add(cur.val);
            }
        }
        return res;
    }
}
