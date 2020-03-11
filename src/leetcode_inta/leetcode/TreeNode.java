package leetcode_inta.leetcode;

/**
 * @author inta
 * @date 2019/7/1
 * @describe 二叉树    相关遍历方式 已经解在题库中 【前序 144 中序 94 后序145】
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }

    public void insert(int value){
        if(value<=val){
            if(left == null){
                left = new TreeNode(value);
            }else{
                left.insert(value);
            }
        }else{
            if(right == null){
                right = new TreeNode(value);
            }else{
                right.insert(value);
            }
        }
    }

    public boolean contains(int value){
        if(value == val){
            return true;
        }else if(value<val){
            if(left == null){
                return false;
            }else{
                return left.contains(value);
            }
        } else{
            if(right == null){
                return false;
            }else{
                return right.contains(value);
            }
            }

    }

    public void printinOrder(){
        if(left!=null){
            left.printinOrder();
        }
        System.out.println(val);
        if(right!=null){
            right.printinOrder();
        }
    }

}
