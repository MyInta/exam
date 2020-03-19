package interview.I6_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author inta
 * @date 2020/3/19
 * @describe 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * 示例1:
 *
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 *
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 *
 * 提示:
 *
 *     字符都是英文字母。
 *     字符串长度在[1, 9]之间。
 */
public class I0808permutation {
    public String[] permutation(String S) {
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        visited = new boolean[127];
        res = new ArrayList<>();
        dfs(chars, new StringBuilder(), 0);
        //list转化为String[]
        return res.toArray(new String[res.size()]);
    }
    private boolean[] visited;
    private List<String> res;
    private void dfs(char[] chars, StringBuilder sb, int cur) {
        if (cur == chars.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            //去重，把相等情况前面若没遍历到就跳过
            if (visited[i] || i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;
            visited[i] = true;
            dfs(chars, sb.append(chars[i]), cur + 1);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
