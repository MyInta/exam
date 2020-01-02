package tencent.leetcode51_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/12/27
 * @describe 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class Q95generateTrees {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        return generateTree(1, n);
    }
    private List<TreeNode> generateTree(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        //说明不存在，加入空即可
        if (start > end) {
            res.add(null);
            return res;
        }
        //对start->end依次作为父节点遍历
        for (int i = start; i <= end; i ++) {
            //制造父节点左边的树
            List<TreeNode> i_left_Tree = generateTree(start, i - 1);
            //同上制造右边的树
            List<TreeNode> i_right_Tree = generateTree(i + 1, end);
            //然后左右两树两两相交
            for (TreeNode left : i_left_Tree) {
                for (TreeNode right : i_right_Tree) {
                    //这是父节点
                    TreeNode node = new TreeNode(i);
                    //为他填上左右两子树
                    node.left = left;
                    node.right = right;
                    //每次创建树都保存进去
                    res.add(node);
                }
            }
        }
        return res;
    }
}
