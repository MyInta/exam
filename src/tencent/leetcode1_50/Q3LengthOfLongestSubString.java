package tencent.leetcode1_50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/9/4
 * @describe 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
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
 */
public class Q3LengthOfLongestSubString {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len <= 1) return len;
        int[] dp = new int[len];
//        dp[len-1] = 1;
        List<Character> list;
        for (int i = len - 1; i >= 0; i--) {
            list = new ArrayList<>();
            list.add(s.charAt(i));
//            int sub = 0;
            for (int j = i - 1; j >= 0; j--) {
                char cur = s.charAt(j);
                if (!list.contains(cur)) {
                    list.add(cur);
                } else {
//                    sub =list.size() - 1;
                    break;
                }
            }
            dp[i] = list.size();
//            i -= sub;
        }
        int res = 0;
        for (int temp:dp) {
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * 滑动窗口 队列从左到右遍历元素，同时记录不重复的队列长度最大值，遇到重复元素时队列左标记右移
     */
    private class Q3LengthOfLongestSubString2{
        public int lengthOfLongestSubstring(String s) {
            int len = s.length();
            if (len <= 1) return len;
            int left = 0;
            int max = 0;
            Map<Character,Integer> hashmap = new HashMap<>();
            for (int i = 0; i < len; i++) {
                if (hashmap.containsKey(s.charAt(i))) {
                    left = Math.max(left, hashmap.get(s.charAt(i)) + 1);
                }
                hashmap.put(s.charAt(i), i);
                max = Math.max(max, i - left + 1);
            }
            return max;
        }
    }


}
