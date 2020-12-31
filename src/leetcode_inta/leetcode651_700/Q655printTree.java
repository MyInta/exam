package leetcode_inta.leetcode651_700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * @author inta
 * @date 2020/11/6
 * @describe 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 *
 *     行数 m 应当等于给定二叉树的高度。
 *     列数 n 应当总是奇数。
 *     根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。
 *     根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。
 *     左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，
 *     但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 *     每个未使用的空间应包含一个空的字符串""。
 *     使用相同的规则输出子树。
 *
 * 示例 1:
 *
 * 输入:
 *      1
 *     /
 *    2
 * 输出:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * 示例 2:
 *
 * 输入:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * 示例 3:
 *
 * 输入:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 *
 */
public class Q655printTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //依据题意，是将二叉树放置到一个容器中，那么就先确定容器大小，然后层层遍历往里填充
    public List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root, 0);
        List<List<String>> lists = new ArrayList<>(depth);
        //每一层的宽度
        int range = (int)Math.pow(2, depth) - 1;
        //填充初始值“”
        for (int i = 0; i < depth; i++) {
            List<String> list = new ArrayList<>(range);
            //这里我原先用的Collections.fill()方法，但是报索引越界，由此发现Coolections.fill()是对原有元素的替换，不是添加
            for (int j = 0; j < range; j++) {
                list.add("");
            }
            lists.add(list);
        }
        //往里填充二叉树
        dfs(root, 0, 0, range - 1, lists);
        return lists;
    }

    //填充二叉树
    private void dfs(TreeNode tn, int level, int left, int right, List<List<String>> lists) {
        if (tn == null || level == lists.size()) return;
        //每次填充对象都是在正中间填充的
        int mid = left + (right + left) / 2;
        lists.get(level).set(mid, Integer.toString(tn.val));
        dfs(tn.left, level + 1, left, mid - 1, lists);
        dfs(tn.right, level + 1, mid + 1, right, lists);
    }

    //获得二叉树的深度
    private int getDepth(TreeNode tn, int depth) {
        if (tn == null) return depth;
        return Math.max(getDepth(tn.left, depth), getDepth(tn.right, depth)) + 1;
    }
}
