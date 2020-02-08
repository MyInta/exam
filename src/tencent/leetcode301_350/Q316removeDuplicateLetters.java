package tencent.leetcode301_350;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/2/8
 * @describe 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，
 * 使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1:
 *
 * 输入: "bcabc"
 * 输出: "abc"
 * 示例 2:
 *
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 */
public class Q316removeDuplicateLetters {
    //使用栈解决问题，栈为空，元素入栈，不为空，比较栈顶元素，若栈顶元素值大且数量>1，出栈新元素入栈，每次出栈数量消减
    //每次添加的时候，还要考虑栈内有该元素，有就没必要再添加
    public String removeDuplicateLetters(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a'] ++;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                //如果之前就包含该元素，直接跳过，数量减一即可
                if (stack.contains(c)) {
                    counts[c - 'a'] --;
                    continue;
                }
                while (!stack.isEmpty() && counts[stack.peek() - 'a'] > 1) {
                    if (c < stack.peek()) {
                        counts[stack.pop() - 'a'] --;
                    } else {
                        break;
                    }
                }
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    //网友和我不同在是直接判断该元素在后序有无出现，不需要统计元素数量，节省计算步骤
    public String removeDuplicateLetters2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i ++) {
            char c = chars[i];
            //如果之前就包含该元素，直接跳过
            if (stack.contains(c)) continue;
            //如果后序存在栈顶元素，且栈顶元素大于当前元素，出栈入栈
            while (!stack.isEmpty() && s.indexOf(stack.peek(), i) != -1 && stack.peek() > c) stack.pop();
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
