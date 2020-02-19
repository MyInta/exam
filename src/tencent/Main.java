package tencent;


import tencent.leetcode101_150.Q105BuildTree;

import java.util.*;

public class Main {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        int i = 5 - 4 % 2;
        System.out.println(i);
    }


    int[] post;
    int cur;
    Map<Integer,Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.post = postorder;
        this.cur = post.length - 1;
        for (int i = 0; i < inorder.length; i ++) {
            map.put(inorder[i], i);
        }
        return helper(0, cur);
    }

    private TreeNode helper(int left, int right) {
        if(left>right){
            return null;
        }
        TreeNode t = new TreeNode(post[cur --]);
        //获得该节点值相等的数组对应索引位置
        int find = map.get(t.val);
        //相当于在inorder数组中继续迭代
        t.left = helper(left,find - 1);
        t.right = helper(find + 1,right);
        return t;
    }
}
