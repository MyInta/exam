package tencent.leetcode401_450;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/24
 * @describe 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其层序遍历:
 *
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 *  
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 */
public class Q429levelOrder {
    private class Node{
        int val;
        List<Node> children;
        private Node() {}
        private Node(int val) {this.val = val;}
        private Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    //非递归
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> list;
        while (!queue.isEmpty()) {
            //关键点，保留每一行的数量，方便遍历查找
            int size = queue.size();
            list = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                Node temp = queue.poll();
                List<Node> children = temp.children;
                for (Node n : children) {
                    if (n != null) {
                        queue.add(n);
                    }
                }
                list.add(temp.val);
            }
            res.add(list);
        }
        return res;
    }

    //试试递归
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        solution(root, 0, res);
        return res;
    }
    private void solution(Node root, int depth, List<List<Integer>> res) {
        if (root == null) return;
        //查看是否是新的一层,是就创层
        if (depth == res.size()) {
            res.add(new ArrayList<>());
        }
        //添加当前元素值
        res.get(depth).add(root.val);
        //给子节点递归操作
        for (Node n : root.children) {
            //起始经检测，发现题目中子节点不存在空情况，但保险起见还是加了
            if (n != null) {
                solution(n, depth + 1, res);
            }
        }
    }

}
