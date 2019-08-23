package tencent.leetcode101_150;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/7/16
 * @describe 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Q105BuildTree {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    int[] in;
    int[] pre;

    public TreeNode buildTree(int[] preorder,int[] inorder){
        this.in = inorder;
        this.pre = preorder;
        TreeNode t = build(0,pre.length-1,0,in.length-1);
        return t;
    }

    public TreeNode build(int st1, int ed1, int st2, int ed2) {
        //因为每次截取的话，最后考虑的还是前段情况，而后段也是以前段作为处理参考
        if(st1>ed1){
            return null;
        }
        TreeNode firstNode = new TreeNode(pre[st1]);
        //表示前后两段交界处索引，
        // 如【[1，3，5，7]，[8。。。]】和【[7，5，3，1]，[9。。。]】中的1所在索引3
        int find = -1;
        //寻找pre中和in前段一号位元素相等的元素索引find
        for(int i=st2;i<=ed2;i++){
            if(in[i] == firstNode.val){
                find = i;
                //不要忘记这个！很重要，避免重复找下一个
                break;
            }
        }
        if(find == -1){
            //即该节点以下不存在结点了，前段pre和in元素仅剩各一个且相等
            return null;
        }
        //找出[3,5,7]长度，即需要递归的新数组长度,即find索引减去初始索引（3-0）三个
        int sub = find - st2;
        //注释的参数用慢速节省了空间，非注释速度快而空间使用多
        firstNode.left = build(st1+1,st1+sub,st2,/*find-1*/st2+sub);
        firstNode.right = build(st1+sub+1,ed1,find+1,ed2);
        return firstNode;
    }

    //====================时间更优======================
    private class method2{
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
            TreeNode t = new TreeNode(pre[cur++]);
            //获得该节点值相等的数组对应索引位置
            int find = inMap.get(t.val);
            //相当于在inorder数组中继续迭代
            t.left = helper(left,find-1);
            t.right = helper(find+1,right);
            return t;
        }

    }


}
