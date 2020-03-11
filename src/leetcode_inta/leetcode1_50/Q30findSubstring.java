package leetcode_inta.leetcode1_50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/25
 * @describe 给定一个字符串 s 和一些长度相同的单词 words。
 * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 *
 */
public class Q30findSubstring {
    //使用两个hashmap，一个存字典，另一个存字符串中遍历找到的字串情况，全满足就添加索引
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        Map<String, Integer> words_map = new HashMap<>();
        Map<String, Integer> subString_map;
        //将字典中的字符全部存储进去,考虑重复情况，统计数量
        for (String word : words) {
            words_map.put(word, words_map.getOrDefault(word, 0) + 1);
        }
        //开始遍历字符，记录可能的出现正确的字典内容数量
        int size = words.length;
        //题意说所有单词长度相等
        int len = words[0].length();
        //条件判断减去的是（匹配字符串总长度-1），因为我们找的是匹配的首个索引位置
        for (int i = 0; i < s.length() - size * len + 1; i ++) {
            //更新字符串hashmap
            subString_map = new HashMap<>();
            //用来记录符合字典中字符的字串数量，当其与size相等，就说明是找到了，记录
            int num = 0;
            while (num < size) {
                //截取一个字串
                String subStr = s.substring(i + num * len, i + num * len + len);
                if (words_map.containsKey(subStr)) {
                    //如果该字串在字典中，我们将其存储进字符串hashmap中，并与字典hashmap比较数量情况
                    int v = subString_map.getOrDefault(subStr, 0) + 1;
                    subString_map.put(subStr, v);
                    if (v > words_map.get(subStr)) {
                        //若是数量超过字典中数量，就说明拼成的字符串是不符合我们要求的，要直接返回找下一个索引
                        break;
                    }
                    //若是顺利到这，就说明一切正常，我们继续判断下一个字符中的元素是否符合字典信息
                    num ++;
                } else {
                    //要是字串不在字典中，直接返回，去找下一个索引
                    break;
                }
            }
            if (num == size) {
                res.add(i);
            }
        }
        return res;
    }


    //根据滑窗方式进行优化
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        Map<String, Integer> words_map = new HashMap<>();
        Map<String, Integer> subString_map;
        //将字典中的字符全部存储进去,考虑重复情况，统计数量
        for (String word : words) {
            words_map.put(word, words_map.getOrDefault(word, 0) + 1);
        }
        //开始遍历字符，记录可能的出现正确的字典内容数量
        int size = words.length;
        //题意说所有单词长度相等
        int len = words[0].length();
        for (int i = 0; i < len; i ++) {
            int left = i, right = i, count = 0;
            subString_map = new HashMap<>();
            //索引范围在[0-字符串减一个单词的长度内)
            while (right + len <= s.length()) {
                String str = s.substring(right, right + len);
                subString_map.put(str, subString_map.getOrDefault(str, 0) + 1);
                //移动右指针
                right += len;
                //统计记录的单词数量
                count ++;
                //考虑如果出现重复数量大于字典内单词重复数量时候,这个判断很巧妙，连非字典内单词情况也考虑了
                while (subString_map.getOrDefault(str, 0) > words_map.getOrDefault(str, 0)) {
                    String remove_str = s.substring(left, left + len);
                    //统计的单词数减一
                    count --;
                    //左指针左移
                    left += len;
                    subString_map.put(remove_str, subString_map.getOrDefault(remove_str, 0) - 1);
                }
                //如果正确统计的单词数量达到了字典内单词数量，就添加索引
                if (count == size) res.add(left);
            }
        }
        return res;
    }

}
