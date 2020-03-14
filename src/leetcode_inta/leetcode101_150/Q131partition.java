package leetcode_inta.leetcode101_150;

import java.util.ArrayList;
import java.util.List;

/**
 * @author inta
 * @date 2019/10/17
 * @describe 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 */
public class Q131partition {
    private List<List<String>> res;
    private List<String> list;
    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        list = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            res.add(list);
            return res;
        }
        char[] s_chars = s.toCharArray();
        generateRes(s_chars, 0, s_chars.length - 1);
        return res;
    }
    //用于判断某一截字符是否为回文的方法
    private boolean isPalindrome(char[] chars, int start, int end) {
        while (end > start) {
            if (chars[end --] != chars[start ++]) return false;
        }
        return true;
    }

    //用于递归产生结果集的方法
    private void generateRes(char[] chars, int start, int end) {
        if (end < start) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= end; i ++) {
            //如果是回文，进入递归
            if (isPalindrome(chars, start, i)) {
                StringBuilder sb = new StringBuilder();
                for (int j = start; j <= i; j ++) {
                    sb.append(chars[j]);
                }
                list.add(sb.toString());
                generateRes(chars, i + 1, end);
                list.remove(list.size() - 1);
            }
        }
    }
}
