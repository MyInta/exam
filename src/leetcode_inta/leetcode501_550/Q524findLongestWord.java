package leetcode_inta.leetcode501_550;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author inta
 * @date 2020/6/5
 * @describe 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，
 * 该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 *
 * 示例 2:
 *
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * 输出:
 * "a"
 *
 * 说明:
 *     所有输入的字符串只包含小写字母。
 *     字典的大小不会超过 1000。
 *     所有输入的字符串长度不会超过 1000。
 *
 */
public class Q524findLongestWord {
    //暴力，按长度排序，然后判断是否是字符串的子串
    public String findLongestWord(String s, List<String> d) {
        String[] ds = new String[d.size()];
        int index = 0;
        for (String str : d) {
            ds[index ++] = str;
        }
        Arrays.sort(ds, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        for (int i = index - 1; i >= 0; i--) {
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (ds[i].charAt(count) == s.charAt(j)) count ++;
                if (count == ds[i].length()) return ds[i];
            }
        }
        return "";
    }

    //官解非排序的算法，就是一遍遍历找解
    public String findLongestWord2(String s, List<String> d) {
        String res = "";
        for (String str : d) {
            //我们需要的是长度最大的,如果是子串，那么就和目前获得的解进行比较
            if (str.length() >= res.length() && issub(str, s)) {
                //现在只能说明这个字符串是子串，并且长度可能比目前获得的结果要大
                if (str.length() == res.length() && str.compareTo(res) < 0 || str.length() > res.length()) {
                    res = str;
                }
            }
        }
        return res;
    }
    //提供一个判断是否是子串的方法
    private boolean issub(String str, String s) {
        //str的索引
        int i = 0;
        for (int j = 0; j < s.length() && i < str.length(); j++) {
            if (s.charAt(j) == str.charAt(i)) i++;
        }
        return i == str.length();
    }
}
