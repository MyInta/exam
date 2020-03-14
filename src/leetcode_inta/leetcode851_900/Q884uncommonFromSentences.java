package leetcode_inta.leetcode851_900;

import java.util.*;

/**
 * @author inta
 * @date 2020/1/28
 * @describe 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
 *
 * 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
 *
 * 返回所有不常用单词的列表。
 *
 * 您可以按任何顺序返回列表。
 * 示例 1：
 *
 * 输入：A = "this apple is sweet", B = "this apple is sour"
 * 输出：["sweet","sour"]
 * 示例 2：
 *
 * 输入：A = "apple apple", B = "banana"
 * 输出：["banana"]
 *  
 *
 * 提示：
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A 和 B 都只包含空格和小写字母。
 *
 */
public class Q884uncommonFromSentences {
    //简单做，两个map搞定
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> mapA = new HashMap<>();
        Map<String, Integer> mapB = new HashMap<>();
        for (String a : A.split(" ")) {
            mapA.put(a, mapA.getOrDefault(a, 0) + 1);
        }
        for (String b : B.split(" ")) {
            mapB.put(b, mapB.getOrDefault(b, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entryA : mapA.entrySet()) {
            if (entryA.getValue() == 1 && !mapB.containsKey(entryA.getKey())) {
                list.add(entryA.getKey());
            }
        }
        for (Map.Entry<String, Integer> entryB : mapB.entrySet()) {
            if (entryB.getValue() == 1 && !mapA.containsKey(entryB.getKey())) {
                list.add(entryB.getKey());
            }
        }
        //此处的转化为String数组操作看下面的官解思路，可以更简洁
        String[] result = new String[list.size()];
        int index = 0;
        for (String str : list) {
            result[index ++] = str;
        }
        return result;
    }

    //官解只用了一个map,因为所求的字符出现次数是一次，AB都保存在一起即可
    public String[] uncommonFromSentences2(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        for (String a : A.split(" ")) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (String b : B.split(" ")) {
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int v = entry.getValue();
            if (v == 1) list.add(key);
        }
        return list.toArray(new String[list.size()]);
    }
}
