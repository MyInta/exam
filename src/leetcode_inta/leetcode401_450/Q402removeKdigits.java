package leetcode_inta.leetcode401_450;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/10
 * @describe 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 */
public class Q402removeKdigits {
    //看了下题意，应该是stack，遇到大的push，遇到比peek小的pop，为空直接push，为零跳过
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) return "0";
        if (k >= num.length()) return "0";
        Stack<Character> stack = new Stack<>();
        char[] chars = num.toCharArray();
        int i = 0;
        for (; i < chars.length; i ++) {
            if (k <= 0) break;
            //只要逆序或等于就删,保持k>0情况下的升序
            while (k > 0 && !stack.isEmpty() && chars[i] < stack.peek()) {
                k --;
                stack.pop();
            }
            //最终添加元素,空情况下，不能添加0首位
            if (!stack.isEmpty() || chars[i] != '0') {
                stack.add(chars[i]);
            }
        }
//        for (char c : num.toCharArray()) {
//            //只要逆序或等于就删,保持k>0情况下的升序
//            while (k > 0 && !stack.isEmpty() && c < stack.peek()) {
//                k --;
//                stack.pop();
//            }
//            //最终添加元素,空情况下，不能添加0首位
//            if (!stack.isEmpty() || c != '0') {
//                stack.add(c);
//            }
//        }
        //最上面判断过，k不会大于等于字符串长度，放心pop
        while (k > 0) {
            k --;
            stack.pop();
        }
//        if (stack.isEmpty()) return "0";
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        if (i < chars.length) {
            sb.append(num.substring(i));
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
