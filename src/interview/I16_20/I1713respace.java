package interview.I16_20;

import javafx.scene.control.SplitPane;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author inta
 * @date 2020/7/9
 * @describe 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"
 * 已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 *
 *
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 *
 * 提示：
 *
 *     0 <= len(sentence) <= 1000
 *     dictionary中总字符数不超过 150000。
 *     你可以认为dictionary和sentence中只包含小写字母。
 */
public class I1713respace {
    public int respace(String[] dictionary, String sentence) {
        int[] dp = new int[sentence.length() + 1];
        for (int i = 1; i <= sentence.length(); i++) {
            //理解为，向右移动一个，假设添加了一个新的字符串，预设此时非字典数为此前数量+1个
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < dictionary.length; j++) {
                String temp = dictionary[j];
                //遍历字典，找到长度小于截取片段的，做判断
                if (temp.length() <= i) {
                    if (temp.equals(sentence.substring(i - temp.length(), i))) {
                        //那么，该索引处，非字典数目为原本的，或者是截取前一段的最少的情况
                        dp[i] = Math.min(dp[i], dp[i - temp.length()]);
                    }
                }
            }
        }
        return dp[sentence.length()];
    }

    //同上思路，整理一下，dp[i]保存索引i之前对应未识别字符串数量
    //每次移动一位，假定当前字符串前面和索引所在字符不构成一个尾部单词可以存在于字典中 dp[i]=dp[i-1]+1，未识别单词数增加
    //如果尾部拼接后存在于字典中，那么就寻找最小，也就是切或不切情况下未识别单词数量
    public int respace2(String[] dictionary, String sentence) {
        Set<String> set = new HashSet<>();
        for (String word : dictionary) set.add(word);
        int[] dp = new int[sentence.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;
            //从i索引往前找尾部切割出来的单词，是否在字典中
            for (int j = 0; j < i; j++) {
                //如果存在于字典中，那么当前i索引的未识别数量可以刷新为原先和j索引处数量最小值
                if (set.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[dp.length - 1];
    }

    //使用后缀单词来进行比较，如果后缀单词压根就没出现过，就不用他
    public int respace3(String[] dictionary, String sentence) {
        Set<String> set = new HashSet<>();
        Map<Character, Set<Integer>> map = new HashMap<>();
        for (String word : dictionary) {
            set.add(word);
            //获取单词最后一个单词
            char c = word.charAt(word.length() - 1);
            //依据单词寻找是否存在于集合中，没有就创建一个统计该单词对应拥有的单词长度信息
            Set<Integer> len_set = map.getOrDefault(c, new HashSet<>());
            len_set.add(word.length());
            map.put(c, len_set);
        }
        int[] dp = new int[sentence.length() + 1];
        for (int i = 1; i <= sentence.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            char end = sentence.charAt(i - 1);
            if (map.containsKey(end)) {
                //遍历单词长度，免去从头找
                for (int len : map.get(end)) {
                    //如果字典中有该长度下的单词，就更新一波
                    if (i >= len && set.contains(sentence.substring(i - len, i))) {
                        dp[i] = Math.min(dp[i], dp[i - len]);
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }


    //使用前缀树来做
    public int respace4(String[] dictionary, String sentence) {
        root = new TrieNode();
        //将前缀树构建出来
        buildTree(dictionary);
        //依据前缀树，从尾部到头来动规，找最少
        int[] dp = new int[sentence.length() + 1];
        for (int i = sentence.length() - 1; i >= 0; i--) {
            //默认情况下，认为其数量是前一个添加了一个新单词构成
            dp[i] = dp[i + 1] + 1;
            TrieNode temp = root;
            for (int j = i; j < sentence.length(); j ++) {
                //从截取位置到末端，来查看，是否有以其结尾的单词，更新数量
                int end_c_index = sentence.charAt(j) - 'a';
                //如果该索引位置开始找到符合的前缀，就更新数量，否则找下一个
                if (temp.children[end_c_index] != null) {
                    //如果遍历到单词尽头，就更新数量
                    if (temp.children[end_c_index].end) {
                        dp[i] = Math.min(dp[i], dp[j + 1]);
                    }
                    temp = temp.children[end_c_index];
                } else break;
            }
        }
        return dp[0];
    }
    private TrieNode root;
    private class TrieNode{
        //该节点下面关联的26个字母空槽
        TrieNode[] children;
        //是否是最后一个单词
        boolean end;
        TrieNode() {
            children = new TrieNode[26];
            end = false;
        }
    }
    //帮助root根节点，构建出前缀树
    private void buildTree(String[] dict) {
        for (String word : dict) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                //如果该字符槽空，就给他填充掉
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                }
                //遍历其下一个节点
                cur = cur.children[index];
            }
            //最后，该节点是单词末端，要进行标记
            cur.end = true;
        }
    }
}
