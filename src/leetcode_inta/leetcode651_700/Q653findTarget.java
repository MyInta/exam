package leetcode_inta.leetcode651_700;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author inta
 * @date 2020/2/4
 * @describe 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *  
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 */
public class Q653findTarget {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    //回溯的思想不错，但是效率不高，看了网上解答，发现思路好多啊
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        return findTwo(root.left, root, k) || findTwo(root, root.right, k) || findTarget(root.left, k) || findTarget(root.right, k);
    }
    private boolean findTwo(TreeNode one, TreeNode two, int k) {
        if (one == null || two == null || one.val >= two.val) return false;
        int sum = one.val + two.val;
        if (sum == k) {
            return true;
        } else if (sum > k) {
            return findTwo(one.left, two, k) || findTwo(one, two.left, k);
        } else {
            return findTwo(one.right, two, k) || findTwo(one, two.right, k);
        }
    }

    //思路2：使用hashset查找有无k-val对象,有直接返回true，无就添加元素，直到元素全部遍历
    private Set<Integer> set = new HashSet<>();
    public boolean findTarget2(TreeNode root, int k) {
        //中序遍历将元素保存进set中
        return setInSet(root, k);
    }
    private boolean setInSet(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return setInSet(root.left, k) || setInSet(root.right, k);
    }

    //思路3：也是先遍历保存元素，中序获得升序数组，然后双指针判断能否取得k
    private List<Integer> list = new ArrayList<>();
    public boolean findTarget3(TreeNode root, int k) {
        //中序添加到list
        addInList(root);
        //双指针
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum > k) {
                right --;
            } else if (sum == k) {
                return true;
            } else {
                left ++;
            }
        }
        return false;
    }
    private void addInList(TreeNode root) {
        if (root == null) return;
        if (root.left != null) addInList(root.left);
        list.add(root.val);
        addInList(root.right);
    }

    //思路4：BST二叉搜索树查找法
    public boolean findTarget4(TreeNode root, int k) {
        if (root == null) return false;
        return checkValid(root, root, k);
    }
    //私有方法，利用BST特性，左小右大，找到符合目标值的一个结点
    private TreeNode findOne(TreeNode node, int target) {
        if (node == null || node.val == target) return node;
        if (node.val > target) {
            return findOne(node.left, target);
        } else {
            return findOne(node.right, target);
        }
    }
    private boolean checkValid(TreeNode node, TreeNode root, int k) {
        if (node == null) return false;
        //每次搜索都是从root出发找的
        TreeNode one = findOne(root, k - node.val);
        //找到的结点不能为空，而且不能和第一个结点相同
        if (one != null && one != node) return true;
        return checkValid(node.left, root, k) || checkValid(node.right, root, k);
    }
}
