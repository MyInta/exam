package offer.V1_50;

/**
 * @author inta
 * @date 2020/2/29
 * @describe 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 *
 * 提示：
 *
 * s.length <= 40000
 *  LC3
 */
public class V48lengthOfLongestSubstring {
    //思想，数组保存滑窗内元素信息，遇到数量大于1的，移动右指针，删到数量符合为止，再继续移动右指针
    public int lengthOfLongestSubstring(String s) {
        int[] counts = new int[128];
        int left = 0, right = 0, len = s.length(), max = 0;
        while (right < len) {
            counts[s.charAt(right)] ++;
            if (counts[s.charAt(right)] > 1) {
                max = Math.max(max, right - left);
            }
            while (counts[s.charAt(right)] > 1) {
                counts[s.charAt(left ++)] --;
            }
            right ++;
        }
        max = Math.max(max, right - left);
        return max;
    }
}
