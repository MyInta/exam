package something.huawei;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author inta
 * @date 2020/11/8
 * @describe
 */
public class Q2 {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] new_line = sc.nextLine().split(" ");
            for (String str : new_line) {
                list.add(Integer.valueOf(str));
            }
            TreeNode root = new TreeNode(list.get(0));
            if (list.size() == 1) {
                System.out.println(root.val);
            } else {
                int index = 1;
                Queue<TreeNode> queue = new LinkedList<>();
                queue.add(root);
                while (!queue.isEmpty() && index < list.size()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        TreeNode temp = queue.poll();
                        if (index < list.size()) {
                            temp.left = new TreeNode(list.get(index++));
                            queue.add(temp.left);
                        }
                        if (index < list.size()) {
                            temp.right = new TreeNode(list.get(index++));
                            queue.add(temp.right);
                        }
                    }
                }
                //此时获得整颗树，但是我们要返回的是非叶子节点的后续遍历
                solution(root);
        }
        }
    }
    private static void solution(TreeNode tn) {
        if (tn == null) return;
        if (tn.left != null) solution(tn.left);
        if (tn.right != null) solution(tn.right);
        if (tn.left != null || tn.right != null) System.out.print(tn.val + " ");
    }
}
