package tencent.leetcode601_650;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/1
 * @describe 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 *
 * 示例 1:
 *
 * 输入: 二叉树: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * 输出: "1(2(4))(3)"
 *
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 *
 * 输入: 二叉树: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * 输出: "1(2()(4))(3)"
 *
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 */
public class Q606tree2str {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        if (t == null) return "";
        sb.append(t.val);
        if (t.left != null && t.right != null)
            sb.append('(').append(tree2str(t.left)).append(')').append('(').append(tree2str(t.right)).append(')');
        else if (t.right != null)
            sb.append("()(").append(tree2str(t.right)).append(')');
        else if (t.left != null)
            sb.append('(').append(tree2str(t.left)).append(')');
        return sb.toString();
    }
    //网友提供了也是另一种顺序，先实现下网友的,写法有点区别，但效率还是很差，不知道为何,是不是因为频繁创建sb缘故？
    public String tree2str2(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        if (t == null) return "";
        sb.append(t.val);
        if (t.left != null || t.right != null) {
            //因为当left为空，根据前面条件，说明right不空，需要一个空括号，反之right为空，也需要下面这行
            sb.append('(').append(tree2str(t.left)).append(')');
            if (t.right != null) {
                sb.append('(').append(tree2str(t.right)).append(')');
            }
        }
        return sb.toString();
    }

    //官解的顺序
    public String tree2str3(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        if (t == null) return "";
        sb.append(t.val);
        if (t.left == null && t.right == null)
            return sb.toString();
        else if (t.right == null)
            return sb.append("(").append(tree2str(t.left)).append(')').toString();
        return sb.append('(').append(tree2str(t.left)).append(')').append('(').append(tree2str(t.right)).append(')').toString();
    }

    //根据方法2修改下，不要频繁创建sb，看看执行效率,效率变得极高，好神奇啊！
    public String tree2str4(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        dfs(t, sb);
        return sb.toString();
    }
    private void dfs(TreeNode t, StringBuilder sb) {
        if (t != null) {
            sb.append(t.val);
            if (t.left != null || t.right != null) {
                //因为当left为空，根据前面条件，说明right不空，需要一个空括号，反之right为空，也需要下面这行
                sb.append('(');
                dfs(t.left, sb);
                sb.append(')');
                if (t.right != null) {
                    sb.append('(');
                    dfs(t.right, sb);
                    sb.append(')');
                }
            }
        }
    }
}
