package tencent.leetcode1151_1200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author inta
 * @date 2019/12/26
 * @describe 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 *
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 *
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 *
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 *
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 *
 */
public class Q1160countCharacters {
    //单词拼写每个字符用一次，那就用map保存，遍历
    public int countCharacters(String[] words, String chars) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //此时map已经保存了所有字符以及字符数量，开始遍历单词
        for (String str : words) {
            Map<Character, Integer> map_temp = new HashMap<>(map);
            boolean valid = true;
            for (char str_c : str.toCharArray()) {
                if (map_temp.containsKey(str_c) && map_temp.get(str_c) > 0) {
                    map_temp.put(str_c, map_temp.get(str_c) - 1);
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) res += str.length();
        }
        return res;
    }

    //大佬们都喜欢直接用数组来实现，效率贼高
    public int countCharacters2(String[] words, String chars) {
        int res = 0;
        int[] count = new int[26];
        for (char c : chars.toCharArray()) {
            count[c - 'a'] ++;
        }
        //此时count保存了字典中所有字符和其数量
        for (String str : words) {
            if (isValid(str, count)) {
                res += str.length();
            }
        }
        return res;
    }
    private boolean isValid(String str, int[] count) {
        int[] temp = new int[26];

        for (char c : str.toCharArray()) {
            if (temp[c - 'a'] == count[c - 'a']) {
                return false;
            }
            temp[c - 'a'] ++;
        }
        return true;
    }
}
