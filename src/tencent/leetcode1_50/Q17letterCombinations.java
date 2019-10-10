package tencent.leetcode1_50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author inta
 * @date 2019/10/10
 * @describe 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2 abc 3 def 4 ghi 5 jkl 6 mno 7 pqrs 8 tuv 9 wxyz
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Q17letterCombinations {
    private List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return res;
        Map<Character, char[]> reflect = new HashMap<>();
        char[] c2 = {'a', 'b', 'c'};
        reflect.put('2', c2);
        char[] c3 = {'d', 'e', 'f'};
        reflect.put('3', c3);
        char[] c4 = {'g', 'h', 'i'};
        reflect.put('4', c4);
        char[] c5 = {'j', 'k', 'l'};
        reflect.put('5', c5);
        char[] c6 = {'m', 'n', 'o'};
        reflect.put('6', c6);
        char[] c7 = {'p', 'q', 'r', 's'};
        reflect.put('7', c7);
        char[] c8 = {'t', 'u', 'v'};
        reflect.put('8', c8);
        char[] c9 = {'w', 'x', 'y', 'z'};
        reflect.put('9', c9);
        combine(digits.toCharArray(), 0, reflect, new StringBuilder());
        return res;
    }
    private void combine(char[] target, int start, Map<Character, char[]> reflect, StringBuilder add) {
        if (start == target.length) {
            res.add(add.toString());
            return;
        }
        char[] getRef = reflect.get(target[start]);
        for (char ref : getRef) {
            add.append(ref);
            combine(target, start + 1, reflect, add);
            add.deleteCharAt(add.lastIndexOf(String.valueOf(ref)));
        }
    }
}
