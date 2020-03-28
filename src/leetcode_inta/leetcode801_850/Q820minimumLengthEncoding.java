package leetcode_inta.leetcode801_850;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author inta
 * @date 2020/3/28
 * @describe 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 *
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 *
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 *
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 *
 *
 * 示例：
 *
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 *
 *
 * 提示：
 *
 *     1 <= words.length <= 2000
 *     1 <= words[i].length <= 7
 *     每个单词都是小写字母 。
 *
 */
public class Q820minimumLengthEncoding {
    //就是说合并后缀相同的元素，然后统计长度即可
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>();
        //先使用暴力法来解决一下问题
        int res = 0;
        for (String word : words) {
            if (set.contains(word)) continue;
            set.add(word);
            res += word.length() + 1;
            for (String str : words) {
                if (!word.equals(str) && str.endsWith(word)) {
                    res -= word.length() + 1;
                    break;
                }
            }
        }
        return res;
    }

    //看见一个大佬使用转置来实现的，就是我原先考虑的后缀他们直接考虑的是逆序后的前缀
    public int minimumLengthEncoding2(String[] words) {
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        Arrays.sort(words);
        int res = 0;
        for (int j = 0; j < words.length - 1; j++) {
            if (words[j + 1].startsWith(words[j])) continue;
            res += words[j].length() + 1;
        }
        return res + words[words.length - 1].length() + 1;
    }

    //看了大佬的思路，复习了一波字典树的操作
    public int minimumLengthEncoding3(String[] words) {
        //我们这边需要倒叙插入，在插入前要考虑字符串是由长到短，不然插入操作会有重复
        Arrays.sort(words, (String a, String b) -> b.length() - a.length());
        int res = 0;
        Tire t = new Tire();
        for (String word : words) {
            res += t.insert(word);
        }
        return res;
    }
    private class Tire{
        TireNode root;
        Tire () {
            root = new TireNode(-1);
        }
        public int insert(String word) {
            TireNode cur = root;
            boolean newWord = false;
            //得倒着插入
            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';
                if (cur.childen[index] == null) {
                    //说明是新的结点
                    newWord = true;
                    //给该位置的结点创建新的字典节点
                    cur.childen[index] = new TireNode(index);
                }
                cur = cur.childen[index];
            }
            return newWord ? word.length() + 1 : 0;
        }
    }
    private class TireNode{
        int val;
        TireNode[] childen = new TireNode[26];
        TireNode (int val){ this.val = val;}
    }
}
