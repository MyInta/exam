package leetcode_inta.leetcode351_400;

/**
 * @author inta
 * @date 2019/10/31
 * @describe 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *  
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class Q387firstUniqChar {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] s_num = new int[26];
        for (int i = 0; i < s.length(); i ++) {
            s_num[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (s_num[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}
