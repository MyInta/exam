package interview.I6_10;

import java.util.*;

/**
 * @author inta
 * @date 2020/4/22
 * @describe 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 *
 * 注意：本题相对原题稍作修改
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 *
 *     所有输入均为小写字母。
 *     不考虑答案输出的顺序。
 *
 */
public class I1002groupAnagrams {
/*    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> index = new ArrayList<>();
        for (String str : strs) {
            boolean containStr = false;
            for (int i = 0; i < index.size(); i++) {
                if (isMatched(str, index.get(i))) {
                    containStr = true;
                    map.get(index.get(i)).add(str);
                    break;
                }
            }
            //如果index中没有str异构体，就添加新值
            if (!containStr) {
                index.add(str);
                List<String> temp = new ArrayList<>();
                temp.add(str);
                map.put(str, temp);
            }
        }
        for (int i = 0; i < index.size(); i++) {
            res.add(map.get(index.get(i)));
        }
        return res;
    }
    private boolean isMatched(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        int[] counts = new int[127];
        for (char c : str1.toCharArray()) {
            counts[c] ++;
        }
        for (char c : str2.toCharArray()) {
            counts[c] --;
            if (counts[c] < 0) return false;
        }
        return true;
    }*/


    //同上思路，更简便的实现
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> temp = map.getOrDefault(key, new ArrayList<>());
            temp.add(str);
            map.put(key, temp);
        }
        return new ArrayList<>(map.values());
    }
}
