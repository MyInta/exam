package leetcode_inta.leetcode351_400;

import java.util.Stack;

/**
 * @author inta
 * @date 2019/10/5
 * @describe 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 */
public class Q394decodeString {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        //一个存储数量
        Stack<Integer> nums = new Stack<>();
        int count = 0;
        nums.add(count);
        //一个存储字符
        Stack<String> strs = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                count = count * 10 + Integer.parseInt(chars[i] + "");
            } else if (chars[i] == '[') {
                //临时存储下字符串
                strs.add(res.toString());
                //以及字符串数量
                nums.add(count);
                //重置字符串
                res = new StringBuilder();
                //重置数量
                count = 0;
            } else if (chars[i] == ']') {
                //取出数量,“重复”后取出字符串，添加到前面
                int n = nums.pop();
                String child = res.toString();
                for (int k = 1; k < n; k ++) {
                    res.append(child);
                }
                child = res.toString();
                res = new StringBuilder(strs.pop() + child);
            } else {
                res.append(chars[i]);
            }
        }
        return res.toString();
    }
}
