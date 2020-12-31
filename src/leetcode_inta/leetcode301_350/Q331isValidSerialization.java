package leetcode_inta.leetcode301_350;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/11/3
 * @describe 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，
 * 我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 *
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 *
 * 示例 1:
 *
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: "1,#"
 * 输出: false
 *
 * 示例 3:
 *
 * 输入: "9,#,#,1"
 * 输出: false
 */
public class Q331isValidSerialization {
    public boolean isValidSerialization(String preorder) {
        String[] treenodes = preorder.split(",");
        //用来记录当前节点被遍历过的数量
        Stack<Integer> stack = new Stack<>();
        //空，先假定认为其合理
        if (treenodes.length == 0 || treenodes[0].equals("#") && treenodes.length == 1) return true;
        //如果根为空，而还存在子节点，就不合理
        if (treenodes[0].equals("#")) return false;
        //根一开始遍历到的数量认为是0
        stack.push(0);
        int cur = 1;
        while (cur < treenodes.length && !stack.isEmpty()) {
            if (!treenodes[cur].equals("#")) {
                stack.push(0);
            } else {
                while (!stack.isEmpty()) {
                    int temp = stack.pop();
                    if (temp == 0) {
                        stack.push(1);
                        break;
                    }
                }
            }
            cur++;
        }
        return cur == treenodes.length && stack.isEmpty();
    }

    //评论区大佬的思路：从后往前遍历，记录#的数量，只有当#数量维持在2个以上可以正常往前遍历，
    //遇到非#就消除两个#并把该节点当作#，即最终是消除一个#数量，以此类推
    //最终消除到根节点时候，正常#数量应该为1，或者在中途中遇到#数量不够用（小于2个）时候，也是没构成前序遍历的
    public boolean isValidSerialization2(String preorder) {
        int count = 0;
        for (int i = preorder.length() - 1; i >= 0; i--) {
            //如果是逗号，就继续遍历
            if (preorder.charAt(i) == ',') continue;
            if (preorder.charAt(i) == '#') {
                count++;
            } else {
                //如果遇到非#字符串，首先考虑多位数
                while (i >= 0 && preorder.charAt(i) != ',') {
                    i--;
                }
                //遇到数值，如果#数量不够用来消除，就说明不是前序
                if (count < 2) return false;
                //否则，消除两个，并且将当前节点当作#添加一个数量，即直接减去一个数量
                count--;
            }
        }
        //最终到根节点时候，#数量应该是根节点所化成的那一个
        return count == 1;
    }
}
