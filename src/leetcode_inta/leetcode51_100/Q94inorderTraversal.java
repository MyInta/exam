package leetcode_inta.leetcode51_100;

import java.util.*;

/**
 * @author inta
 * @date 2019/8/20
 * @describe
 */
public class Q94inorderTraversal {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root!=null){
        if(root.left!=null){
            inorderTraversal(root.left);
        }
        list.add(root.val);
        if(root.right!=null){
            inorderTraversal(root.right);
        }
        }
        return list;
    }

    //==============迭代==================
    private class method{
        public List<Integer> inorderTraversal(TreeNode root) {
            Deque<TreeNode> deque = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            TreeNode cur = root;
            while(cur!=null||!deque.isEmpty()){
                while(cur!=null){
                    deque.push(cur);
                    cur = cur.left;
                }
                cur = deque.pop();
                list.add(cur.val);
                cur = cur.right;
            }
            return list;
        }
    }



}
