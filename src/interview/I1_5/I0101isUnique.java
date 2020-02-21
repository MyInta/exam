package interview.I1_5;

/**
 * @author inta
 * @date 2020/2/19
 * @describe 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 *
 */
public class I0101isUnique {
    public boolean isUnique(String astr) {
        int[] counts = new int[128];
        for (char c : astr.toCharArray()) {
            if (counts[c] > 0) return false;
            counts[c] ++;
        }
        return true;
    }
}
