package leetcode_inta.leetcode1001_1050;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/6/18
 * @describe 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *
 *
 * 示例 1：
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * 示例 2：
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 示例 3：
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *
 *
 *
 * 提示：
 *
 *     原始树中的节点数介于 1 和 1000 之间。
 *     每个节点的值介于 1 和 10 ^ 9 之间。
 *
 */
public class Q1028recoverFromPreorder {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //先写思路清晰的，单纯从左往右遍历，一开始建立左树，左树存在再建立右树，使用map记录和查询父节点
    public TreeNode recoverFromPreorder(String S) {
        //标记该节点属于第几层
        int level = 0;
        //用来标记第几层以及对应节点
        Map<Integer, TreeNode> map = new HashMap<>();
        //当前遍历字符串的索引;以及保留的数值
        int cur = 0, val = 0;
        while (cur < S.length() && S.charAt(cur) != '-') {
            val = val * 10 + (S.charAt(cur) - '0');
            cur ++;
        }
        TreeNode tn = new TreeNode(val);
        //注意重置下val
        val = 0;
        map.put(level, tn);
        while (cur < S.length()) {
            while (cur < S.length() && S.charAt(cur) == '-') {
                cur ++;
                //表明属于的层次上升
                level ++;
            }
            //碰到数值或者边界
            while (cur < S.length() && S.charAt(cur) != '-') {
                val = val * 10 + (S.charAt(cur) - '0');
                cur ++;
            }
//            System.out.println(val);
            TreeNode temp = new TreeNode(val);
            //从map获得其父节点
            TreeNode parent = map.get(level - 1);
            //如果父节点的左子树空缺，优先填左子树，因为我们是从左往右遍历的
            if (parent.left == null) {
                parent.left = temp;
            } else {
                parent.right = temp;
            }
            map.put(level, temp);
            //重置
            level = 0;
            val = 0;
        }
        //返回辣个男人
        return tn;
    }
}
