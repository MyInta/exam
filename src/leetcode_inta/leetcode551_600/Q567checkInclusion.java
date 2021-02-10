package leetcode_inta.leetcode551_600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/13
 * @describe 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class Q567checkInclusion {
    // 使用map来存储，效率低，但是应该可行,的确可行，然而速度是2s，没错，不是ms是s。。。
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int map_size = s1.length();
        char[] s2_chars = s2.toCharArray();
        //s2遍历的指针
        int cur = 0;
        while (cur < s2_chars.length) {
            if (map.containsKey(s2_chars[cur])) {
                Map<Character, Integer> clone_map = new HashMap<>();
                clone_map.putAll(map);
                int counts = 0;
                int index = cur;
                while (index < s2.length() && clone_map.containsKey(s2_chars[index]) && clone_map.get(s2_chars[index]) > 0) {
                    counts++;
                    clone_map.put(s2_chars[index], clone_map.get(s2_chars[index]) - 1);
                    index++;
                }
                if (counts == map_size) return true;
            }
            cur++;
        }
        return false;
    }


    // 同上流程，但使用数组来进行存储的话，效率应该能提升许多,同样思路，一下子砍成了82ms
    public boolean checkInclusion2(String s1, String s2) {
        int[] counts = new int[26];
        //统计s1字符出现次数
        for (char c : s1.toCharArray()) {
            counts[c - 'a']++;
        }
        int len = s1.length();
        char[] s2_chars = s2.toCharArray();
        int cur = 0;
        while (cur < s2_chars.length) {
            if (counts[s2_chars[cur] - 'a'] > 0) {
                //如果存在于s1中
                int[] counts_copy = new int[counts.length];
                System.arraycopy(counts, 0, counts_copy, 0, counts.length);
                int nums = 0;
                int index = cur;
                while (index < s2_chars.length && counts_copy[s2_chars[index] - 'a'] > 0) {
                    nums++;
                    counts_copy[s2_chars[index] - 'a']--;
                    index++;
                }
                if (nums == len) return true;
            }
            cur++;
        }
        return false;
    }

    // 优秀的思路，应该是如使用滑窗 5ms！
    public boolean checkInclusion3(String s1, String s2) {
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();
        if (s2chars.length < s1chars.length) return false;
        for (int i = 0; i < s1chars.length; i ++) {
            s1map[s1chars[i] - 'a']++;
            s2map[s2chars[i] - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (isMatched(s1map, s2map)) return true;
            s2map[s2chars[i + s1.length()] - 'a']++;
            s2map[s2chars[i] - 'a']--;
        }
        return isMatched(s1map, s2map);
    }
    // 匹配下，两个数组是否值都一致或者直接用Arrays.equal()判断
    private boolean isMatched(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // 使用双指针来解题，思路是找区间内统计数量符合要求长度为s1长度的区间 3ms 超越99.88%
    public boolean checkInclusion4(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[] counts = new int[127];
        for (char c : s1.toCharArray()) {
            counts[c]--;
        }
        int left = 0;
        int right = 0;
        while (right < m) {
            char temp = s2.charAt(right);
            counts[temp]++;
            while (counts[temp] > 0) {
                counts[s2.charAt(left)]--;
                left++;
            }
            if (right - left + 1 == n) {
                return true;
            }
            right++;
        }
        return false;
    }
}
