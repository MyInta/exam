package offer.V1_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author inta
 * @date 2020/3/2
 * @describe 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *  
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *  
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 *
 */
public class V38permutation {
    private List<String> lists;
    private boolean[] visited;
    public String[] permutation(String s) {
        char[] sc = s.toCharArray();
        Arrays.sort(sc);
        lists = new ArrayList<>();
        //记录该位置是否被访问过
        visited = new boolean[sc.length];
        dfs(sc, 0, sc.length, new StringBuilder());
        return lists.toArray(new String[lists.size()]);
    }
    private void dfs(char[] sc, int start, int end, StringBuilder sb) {
        if (start == end) {
            lists.add(sb.toString());
            return;
        }
        for (int i = 0; i < end; i ++) {
            //如果是访问过的，或者虽然没访问过但是在前面相同元素还没访问之前，就找下一个
            if (visited[i] || i > 0 && sc[i] == sc[i - 1] && !visited[i - 1]) continue;
            //当前元素现在被访问到了，标记下
            visited[i] = true;
            //把这个元素添加到字符串中
            sb.append(sc[i]);
            dfs(sc, start + 1, end, sb);
            //回溯
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
}
