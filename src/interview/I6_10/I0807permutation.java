package interview.I6_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/14
 * @describe 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * 示例1:
 *
 *  输入：S = "qwe"
 *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 *
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 *
 */
public class I0807permutation {
    public String[] permutation(String S) {
        res = new ArrayList<>();
        chars = S.toCharArray();
        dfs(0, S.length());
        return res.toArray(new String[res.size()]);
    }
    private List<String> res;
    private char[] chars;
    private void dfs(int cur, int end) {
        if (cur == end) {
            res.add(String.valueOf(chars));
            return;
        }
        for (int i = cur; i < end; i ++) {
            swap(cur, i);
            dfs(cur + 1, end);
            swap(cur, i);
        }
    }
    private void swap(int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }
}
