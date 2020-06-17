package leetcode_inta.leetcode1_50;

import java.util.Arrays;

/**
 * @author inta
 * @date 2019/9/28
 * @describe 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 */
public class Q14longestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1);
                if (pre.isEmpty()) return "";
            }
        }
        return pre;
    }


    //上面是八月前写的，对比了下，思路更清晰了，执行代码也不错
    public String longestCommonPrefix2(String[] strs) {
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        if (strs.length == 0) return "";
        String first = strs[0];
        String last = strs[strs.length - 1];
        for (int i = 0; i < first.length() && i < last.length(); i++) {
            if (first.charAt(i) == last.charAt(i)) {
                sb.append(first.charAt(i));
            }
            else break;
        }
        return sb.toString();
    }

}
