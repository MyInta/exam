package tencent.exam_main.leetcode_exam;

import java.util.*;

/**
 * @author inta
 * @date 2019/12/29
 * @describe
 */
public class D1229_2 {
    private class TreeNode{
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> r1 = new Stack<>();
        Stack<TreeNode> r2 = new Stack<>();
        if (root1 != null) {
            r1.add(root1);
        }
        if (root2 != null) {
            r2.add(root2);
        }
        while (!r1.isEmpty()) {
            TreeNode r1_tn = r1.pop();
            res.add(r1_tn.val);
            if (r1_tn.left != null) {
                r1.add(r1_tn.left);
            }
            if (r1_tn.right != null) {
                r1.add(r1_tn.right);
            }
        }
        while (!r2.isEmpty()) {
            TreeNode r2_tn = r2.pop();
            res.add(r2_tn.val);
            if (r2_tn.left != null) {
                r2.add(r2_tn.left);
            }
            if (r2_tn.right != null) {
                r2.add(r2_tn.right);
            }
        }
        res.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        return res;
    }
}
