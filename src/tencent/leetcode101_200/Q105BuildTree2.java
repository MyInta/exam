package tencent.leetcode101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/7/16
 * @describe 时间更优
 */
public class Q105BuildTree2 {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    int[] pre;
    int[] in;
    int cur = 0;
    Map<Integer,Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder,int[] inorder){
        this.pre = preorder;
        this.in = inorder;
        //将inorder里面的所有元素包装到map中去
        int map_idx = 0;
        for(int i:inorder){
            inMap.put(i,map_idx++);
        }
        return helper(0,inorder.length-1);
    }

    private TreeNode helper(int left, int right) {
        if(left>right){
            return null;
        }
        TreeNode t = new TreeNode(pre[cur]);
        //获得该节点值相等的数组对应索引位置
        int find = inMap.get(t.val);
        cur++;
        //相当于在inorder数组中继续迭代
        t.left = helper(left,find-1);
        t.right = helper(find+1,right);
        return t;
    }


}
