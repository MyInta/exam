package leetcode_inta.leetcode251_300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author inta
 * @date 2019/10/4
 * @describe 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 */
public class Q297Codec {

    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    // Encodes a tree to a single string.
    //使用层次遍历
    public String serialize(TreeNode root) {
        //用来存储添加的字符
        StringBuilder sb = new StringBuilder();
        //需要一个队列作为工具
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#,");
            } else {
                sb.append(cur.val).append(",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        String res = sb.toString();
        res = res.substring(0, res.length() - 1);
//        System.out.println(res);
        return res;
    }

    // Decodes your encoded data to tree.
    //字符串分割遍历添加
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        System.out.println(strs[0]);
        if (strs[0].equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(strs[0]));
//        System.out.println(Integer.valueOf(strs[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int index = 1;
        while (index < strs.length) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                TreeNode left = strs[index].equals("#") ? null : new TreeNode(Integer.valueOf(strs[index]));
                cur.left = left;
                index ++;
                queue.add(left);

                TreeNode right = strs[index].equals("#") ? null : new TreeNode(Integer.valueOf(strs[index]));
                cur.right = right;
                index ++;
                queue.add(right);
            }
        }
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
