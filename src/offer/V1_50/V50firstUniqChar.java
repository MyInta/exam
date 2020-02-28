package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/28
 * @describe 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 *
 */
public class V50firstUniqChar {
    //最简单的，就是两遍，一遍统计，一遍查找
    public char firstUniqChar(String s) {
        int[] counts = new int[128];
        char[] s_chars = s.toCharArray();
        for (char c : s_chars) {
            counts[c] ++;
        }
        for (char c : s_chars) {
            if (counts[c] == 1) return c;
        }
        return ' ';
    }
}
