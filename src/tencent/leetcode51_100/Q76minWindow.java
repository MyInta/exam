package tencent.leetcode51_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/9
 * @describe 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 */
public class Q76minWindow {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0;
        int right = 0;
        //用于记录元素数量
        int count = 0;
        //每次找到的字符串长度都需要考虑下，是否是当前最短
        int minLen = Integer.MAX_VALUE;
        //再需要一个int记录字符串索引开始的地方
        int start = -1;
        //用于存储匹配的字符串，以及对应字符的数量
        Map<Character, Integer> t_map = new HashMap<>();
        //用于存储滑窗中的字符串，以及字符串的数量
        Map<Character, Integer> cur_map = new HashMap<>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        //遍历匹配字符串，计数
        for (char t_c : tChars) {
            t_map.put(t_c, t_map.getOrDefault(t_c, 0) + 1);
        }
        //在滑窗过程中，进行map增减元素操作
        while (right < s.length()) {
            //如果该字符是属于需要匹配的，就往窗口字符串中添加元素，并根据之前有无存储，添加元素数量
            if (t_map.containsKey(sChars[right])) {
                cur_map.put(sChars[right], cur_map.getOrDefault(sChars[right], 0) + 1);
                //如果该元素数量和匹配中的元素数量相等，就把计数+1，相当于匹配完毕一个字符了
                if (cur_map.get(sChars[right]).equals(t_map.get(sChars[right]))) {
                    count ++;
                }
            }
            right ++;
            //如果计数和匹配目标长度相等，说明找到适合的字符串了,比较的是map的尺寸（V已经满足的前提）
            while (count == t_map.size()) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                if (t_map.containsKey(sChars[left])) {
                    //如果从左移动的该字符数量和目标数量一样，在减少后必定元素数量更改，即count--
                    if (cur_map.get(sChars[left]).equals(t_map.get(sChars[left]))) {
                        count --;
                    }
                    //然后修改map内元素对应数量
                    cur_map.put(sChars[left], cur_map.get(sChars[left]) - 1);
                }
                //移动窗口左端，并修改记录的数值
                left ++;
            }
        }
        //若起始位置一直没有被修改，说明不存在匹配字段，返回字符串“”，否则，截取后返回字符串
        return start == -1 ? "" : s.substring(start, start + minLen);
    }
}
