package tencent.leetcode101_150;

import java.util.*;

/**
 * @author inta
 * @date 2019/8/14
 * @describe 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 */
public class Q144preorderTraversal {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return list;
        }else{
            list.add(root.val);
        }
        if(root.left!=null){
            preorderTraversal(root.left);
        }
        if(root.right!=null){
            preorderTraversal(root.right);
        }
        return list;
    }

    //===============迭代======================
    private class method2{
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        public List<Integer> preorderTraversal(TreeNode root) {
            if(root == null){
                return res;
            }
            TreeNode cur = root;
            while(cur!=null||!deque.isEmpty()){
                while(cur!=null){
                    res.add(cur.val);
                    deque.push(cur);
                    cur = cur.left;
                }
                cur = deque.pop().right;

            }
            return res;
        }

    }
    //==============迭代2=================
    private class method3{
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        public List<Integer> preorderTraversal(TreeNode root) {
            if(root == null){
                return list;
            }
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                list.add(cur.val);
                if(cur.right!=null){
                    stack.push(cur.right);
                }
                if(cur.left!=null){
                    stack.push(cur.left);
                }
            }
            return list;
        }
    }
}

