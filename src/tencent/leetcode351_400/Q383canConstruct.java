package tencent.leetcode351_400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2020/1/8
 * @describe 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
 * 判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 */
public class Q383canConstruct {
    //最简单的方式就是使用map来生成
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char r : ransomNote.toCharArray()) {
            if (!map.containsKey(r) || map.get(r) == 0) return false;
            map.put(r, map.get(r) - 1);
        }
        return true;
    }

    //但肯定还有别的优化的好方法，提高效率，可以考虑一波数组记录方式,效率提升了一个档次！
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] counts = new int[26];
        for (char c : magazine.toCharArray()) {
            counts[c - 'a'] ++;
        }
        for (char r : ransomNote.toCharArray()) {
            if (counts[r - 'a'] == 0) return false;
            counts[r - 'a'] --;
        }
        return true;
    }
}
