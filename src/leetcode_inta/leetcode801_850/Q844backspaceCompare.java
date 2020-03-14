package leetcode_inta.leetcode801_850;

import java.util.Stack;

/**
 * @author inta
 * @date 2020/1/10
 * @describe 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * 示例 2：
 *
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * 示例 3：
 *
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * 示例 4：
 *
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 *
 */
public class Q844backspaceCompare {
    //个人直觉使用指针来做
    public boolean backspaceCompare(String S, String T) {
        int s_index = S.length() - 1, t_index = T.length() - 1;
        //统计有多少个‘#’
        int back_count = 0;
        while (s_index >= 0 || t_index >= 0) {
            while (t_index >= 0 && (T.charAt(t_index) == '#' || back_count > 0)) {
                if (T.charAt(t_index) == '#') {
                    back_count ++;
                } else {
                    back_count --;
                }
                t_index --;
            }
            //归零
            back_count = 0;
            while (s_index >= 0 && (S.charAt(s_index) == '#' || back_count > 0)) {
                if (S.charAt(s_index) == '#') {
                    back_count ++;
                } else {
                    back_count --;
                }
                s_index --;
            }
            back_count = 0;
            if (s_index >= 0 && t_index >= 0 && S.charAt(s_index) == T.charAt(t_index)) {
                s_index --;
                t_index --;
            } else {
                break;
            }
        }
        return s_index < 0 && t_index < 0;
    }

    //上述双指针可以做，效率也极高，但是写得比较冗余，用栈会很清晰,耗时也不多
    public boolean backspaceCompare2(String S, String T) {
        Stack<Character> stack_s = new Stack<>();
        Stack<Character> stack_t = new Stack<>();
        //分别给两栈压入非#的字母
        for (char c : S.toCharArray()) {
            if (c != '#') {
                stack_s.push(c);
            } else if (!stack_s.isEmpty()) {
                stack_s.pop();
            }
        }
        for (char c : T.toCharArray()) {
            if (c != '#') {
                stack_t.push(c);
            } else if (!stack_t.isEmpty()) {
                stack_t.pop();
            }
        }
        //最后比较栈内元素
        if (stack_s.size() != stack_t.size()) return false;
        while (!stack_s.isEmpty() && !stack_t.isEmpty()) {
            if (stack_s.pop() != stack_t.pop()) return false;
        }
        return true;
        //下面这个判断有点费时
//        return String.valueOf(stack_s).equals(String.valueOf(stack_t));
    }
}
