package leetcode_inta.leetcode401_450;

/**
 * @author inta
 * @date 2021/2/2
 * @describe 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过 10^4。
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class Q424characterReplacement {
    // 老老实实实现方式做一下，遇到不同字母在k次范围内替换，过程统计最长
    public int characterReplacement(String s, int k) {
        int index = 0;
        int max = 0;
        while (index < s.length()) {
            char curChar = s.charAt(index);
            int tempK = k;
            int i = index + 1;
            int next = i;
            for (; i < s.length(); i++) {
                if (s.charAt(i) != curChar) {
                    tempK--;
                }
                if (tempK < 0) {
                    break;
                }
                if (tempK == k) {
                    next++;
                }
            }
            int newLen = i - index;
            if (tempK > 0) {
                newLen += Math.min(tempK, index);
            }
            max = Math.max(max, newLen);
            index = next;
        }
        return max;
    }

    // 使用滑动窗口，窗口内保存字母信息
    public int characterReplacement2(String s, int k) {
        int windowMax = 0;
        int index = 0;
        int[] counts = new int[127];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)]++;
            windowMax = Math.max(windowMax, counts[s.charAt(i)]);
            if (i - index + 1 - windowMax > k) {
                counts[s.charAt(index)]--;
                index++;
            }
            res = Math.max(res, i - index + 1);
        }
        return res;
    }

    // 参考官解，优化重复计算
    public int characterReplacement3(String s, int k) {
        int windowMax = 0;
        int left = 0;
        int right = 0;
        int[] counts = new int[127];
        for (; right < s.length(); right++) {
            counts[s.charAt(right)]++;
            windowMax = Math.max(windowMax, counts[s.charAt(right)]);
            if (right - left + 1 - windowMax > k) {
                counts[s.charAt(left++)]--;
            }
        }
        return right - left;
    }
}
