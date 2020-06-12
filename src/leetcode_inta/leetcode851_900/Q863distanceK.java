package leetcode_inta.leetcode851_900;

import java.util.*;

/**
 * @author inta
 * @date 2020/6/12
 * @describe 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *
 *
 *
 * 提示：
 *
 *     给定的树是非空的。
 *     树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 *     目标结点 target 是树上的结点。
 *     0 <= K <= 1000.
 */
public class Q863distanceK {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap<>();
        //构建所有节点，以及可以通过节点找到上一个节点
        dfs(root, root);
        //从节点target开始找
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        //使用set保存是否访问过
        Set<TreeNode> set = new HashSet<>();
        set.add(target);

        //记录访问的层次距离
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (distance == K) {
                //如果已经满足题意要求的距离，队列中都是该层符合要求的元素
                List<Integer> res = new LinkedList<>();
                for (TreeNode tn : queue) {
                    res.add(tn.val);
                }
                return res;
            }
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null && !set.contains(temp.left)) {
                    //这个节点算访问过了
                    set.add(temp.left);
                    //并且放到队列中，准备下一层时候访问
                    queue.add(temp.left);
                }
                //同上
                if (temp.right != null && !set.contains(temp.right)) {
                    set.add(temp.right);
                    queue.add(temp.right);
                }
                //并且考虑往上的距离，寻找没访问过的父节点
                TreeNode par = parent.get(temp);
                if (!set.contains(par)) {
                    set.add(par);
                    queue.add(par);
                }
            }
            //访问下一层
            distance ++;
        }
        //如果都没啥返回的，就说明没找到目标值，返回空
        return new ArrayList<>();
    }
    private Map<TreeNode, TreeNode> parent;
    //构建节点回溯,使得parent map可以找到节点上一个节点
    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
