package leetcode_inta.leetcode101_150;

import java.util.LinkedList;

/**
 * @author inta
 * @date 2019/8/14
 * @describe 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 */
public class Q111minDepth {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    //========================广度优先=============================
    public class Pair{
        TreeNode tn;
        int depth;
        Pair(TreeNode tn,int depth){
            this.tn = tn;
            this.depth = depth;
        }
    }
    public int minDepth(TreeNode root) {
        if(root ==null){
            return 0;
        }
        LinkedList<Pair> ll = new LinkedList<>();
        ll.add(new Pair(root,1));
        Pair cur;
        int depth = 0;
        while(!ll.isEmpty()){
            cur = ll.poll();
            root = cur.tn;
            depth = cur.depth;
            if(root.left!=null){
                ll.add(new Pair(root.left,depth+1));
            }
            if(root.right!=null){
                ll.add(new Pair(root.right,depth+1));
            }
            if(root.left==null&&root.right==null){
                break;
            }
        }
        return depth;
    }

    //========================递归=============================
    public int minDepth02(TreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null&&root.right == null){
            return 1;
        }
        int depth = Integer.MAX_VALUE;
        if(root.left!= null){
            depth =  Math.min(minDepth02(root.left),depth);
        }
        if(root.right!=null){
            depth =  Math.min(minDepth02(root.right),depth);
        }
        return depth+1;
    }

}
