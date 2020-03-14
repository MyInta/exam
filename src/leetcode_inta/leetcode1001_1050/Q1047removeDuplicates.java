package leetcode_inta.leetcode1001_1050;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/12/25
 * @describe 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 *  
 *
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
 * 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 */
public class Q1047removeDuplicates {
    //set，从左往右找过去一遍
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        char[] s_chars = S.toCharArray();
        for (char c : s_chars) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    //如果我不使用stack可以实现吗？指针？
    public String removeDuplicates2(String S) {
        //使用指针来保留回退
        int left = 0;
        //装载，类似于stack的替换
        char[] temp = new char[S.length()];
        for (int i = 0; i < S.length(); i ++) {
            //当遍历到和前一个元素相同情况下，我们回退指针
            if (left != 0 && S.charAt(i) == temp[left - 1]) {
                left --;
            } else {
                //否则，就保留这个元素
                temp[left ++] = S.charAt(i);
            }
        }
        //此时，指针是在实际我们需要保存的数组索引后一位
        return String.valueOf(temp).substring(0, left);
    }
}
